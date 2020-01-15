package com.yiseven.zhoudaxiao.web.request;

import lombok.Data;

@Data
public class ProductQueryRequest {

    private Integer pageNum;

    private Integer pageSize;

    private Integer categoryId;

    private Boolean isNew;

    /**
     * 根据什么排序
     */
    private String orderBy;

    /**
     * 降序还是升序
     */
    private String sortType;

    private Boolean showAll;

    private String productName;
}
