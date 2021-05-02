package com.imooc.controller;

import com.imooc.service.ItemEsService;
import com.imooc.utils.JSONResult;
import com.imooc.utils.PagedGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsEsController {

    @Autowired
    private ItemEsService itemEsService;

    @GetMapping("/es/search")
    public JSONResult search(
            String keywords,
            String sort,
            Integer page,
            Integer pageSize) {

        if (StringUtils.isBlank(keywords)) {
            return JSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 20;
        }

        page--;

        PagedGridResult grid = itemEsService.searchItems(keywords,
                sort,
                page,
                pageSize);

        return JSONResult.ok(grid);
    }
}
