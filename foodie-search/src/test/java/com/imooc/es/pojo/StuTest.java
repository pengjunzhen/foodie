package com.imooc.es.pojo;


import com.imooc.SearchApplication;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.ConstantScoreQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SearchApplication.class})
class StuTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 不建议使用 ElasticsearchTemplate 对索引进行管理（创建索引，更新映射，删除索引）
     * 索引就像是数据库或者数据库中的表，我们平时是不会是通过java代码频繁的去创建修改删除数据库或者表的
     * 我们只会针对数据做CRUD的操作
     * 在es中也是同理，我们尽量使用 ElasticsearchTemplate 对文档数据做CRUD的操作
     * 1. 属性（FieldType）类型不灵活
     * 2. 主分片与副本分片数无法设置
     */

    @Test
    public void createIndexStu() {
        Stu stu = new Stu();
        stu.setStuId(1005L);
        stu.setName("iron man");
        stu.setAge(54);
        stu.setMoney(1999.8f);
        stu.setSign("I am iron man");
        stu.setDescription("I have a iron army");

        IndexQuery indexQuery = new IndexQueryBuilder().withObject(stu).build();
        String documentId = elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of("stu"));
        Assertions.assertEquals(String.valueOf(stu.getStuId()), documentId);
    }

    @Test
    public void deleteIndexStu() {
        boolean deleted = elasticsearchRestTemplate.indexOps(Stu.class).delete();
        Assertions.assertTrue(deleted);
    }


    //    ------------------------- 我是分割线 --------------------------------

    @Test
    public void updateStuDoc() {

        Map<String, Object> map = new HashMap<>();
        map.put("age", 20);
        map.put("money", 88.88f);

        UpdateQuery updateQuery = UpdateQuery.builder("1005")
                .withDocument(Document.from(map))
                .build();

        UpdateResponse updateResponse = elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of("stu"));
        System.out.println(updateResponse.getResult().toString());
    }

    @Test
    public void getStuDoc() {

        ConstantScoreQueryBuilder constantScoreQueryBuilder = QueryBuilders.constantScoreQuery(
                QueryBuilders.boolQuery().should(
                        QueryBuilders.termQuery("stuId", 1005)));

        Query query = new NativeSearchQueryBuilder()
                .withQuery(constantScoreQueryBuilder)
                .build();
        SearchHits<Stu> searchResult = elasticsearchRestTemplate.search(query, Stu.class);
        Assertions.assertEquals(1L, searchResult.getTotalHits());
        searchResult.getSearchHits().forEach(stuSearchHit -> {
            System.out.println(stuSearchHit.getContent());
        });
    }

    /**
     * 带分页的查询
     */
    @Test
    public void SearchStuDocWithPage() {
        String preTag = "<font color='red'>";
        String postTag = "</font>";

        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("description", "man"))
                .withPageable(PageRequest.of(0, 3))
//                .withSort(SortBuilders.fieldSort("stuId").order(SortOrder.DESC))
                // 上面那种排序方式也可以
                .withSort(new FieldSortBuilder("stuId").order(SortOrder.DESC))
                .withHighlightFields(
                        new HighlightBuilder.Field("description")
                                .preTags(preTag)
                                .postTags(postTag))
                .build();
        SearchHits<Stu> search = elasticsearchRestTemplate.search(nativeSearchQuery, Stu.class);

        search.getSearchHits().forEach(stuSearchHit -> {
            System.out.println(stuSearchHit.getContent());
            System.out.println(stuSearchHit.getHighlightFields());
        });
        Assertions.assertEquals(2L, search.getTotalHits());
    }
}