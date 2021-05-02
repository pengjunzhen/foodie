package com.imooc.service.impl;

import com.imooc.es.pojo.Stu;
import com.imooc.service.ItemEsService;
import com.imooc.utils.PagedGridResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ItemEsServiceImpl implements ItemEsService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public ItemEsServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize) {
        String preTag = "<font color='red'>";
        String postTag = "</font>";

        Pageable pageable = PageRequest.of(page, pageSize);

        SortBuilder sortBuilder = null;
        if (sort.equals("c")) {
            sortBuilder = new FieldSortBuilder("sellCounts")
                    .order(SortOrder.DESC);
        } else if (sort.equals("p")) {
            sortBuilder = new FieldSortBuilder("price")
                    .order(SortOrder.ASC);
        } else {
            // es中如果是Text文本格式的，需要用 对应字段的 keyword才能够进行排序，否则会报错
            sortBuilder = new FieldSortBuilder("itemName.keyword")
                    .order(SortOrder.ASC);
        }

        String itemNameFiled = "itemName";
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery(itemNameFiled, keywords))
                .withSort(sortBuilder)
                .withHighlightFields(
                        new HighlightBuilder.Field("description")
                                .preTags(preTag)
                                .postTags(postTag))
                .withPageable(pageable)
                .build();
        SearchHits<Stu> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Stu.class);
        // TODO 这里还需要将 高亮的部分处理一下
        // 高亮的部分在： searchHits -> searchHit -> highlightFields里（LinkedHashMap中存储）
        PagedGridResult gridResult = new PagedGridResult();
        gridResult.setRows(searchHits.getSearchHits().stream().map(stuSearchHit -> stuSearchHit.getContent()).collect(Collectors.toList()));
        gridResult.setPage(page + 1);
        gridResult.setTotal((int) (searchHits.getTotalHits() / pageable.getPageSize() + 1));
        gridResult.setRecords(searchHits.getTotalHits());

        return gridResult;
    }
}
