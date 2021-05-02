package com.imooc.service;

import com.imooc.utils.PagedGridResult;

public interface ItemEsService {

     PagedGridResult searchItems(String keywords,
                                 String sort,
                                 Integer page,
                                 Integer pageSize);
}
