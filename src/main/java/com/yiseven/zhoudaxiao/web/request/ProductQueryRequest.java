package com.yiseven.zhoudaxiao.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductQueryRequest {

    private Integer pageNum;

    private Integer pageSize;

    private Integer categoryId;

    private Integer isNew;

    /**
     * 根据什么排序
     */
    private String orderBy;

    /**
     * 降序还是升序
     */
    private String sortType;
}
