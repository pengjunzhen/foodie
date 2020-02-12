package com.imooc.pojo.vo;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import lombok.Data;

import java.util.List;

/**
 * @author pengjunzhen
 * @description 商品信息查询类
 * @date 2020/2/12 12:07
 */
@Data
public class ItemInfoVO {

    private Items item;

    private List<ItemsImg> itemImgList;

    private List<ItemsSpec> itemSpecList;

    private ItemsParam itemParams;
}
