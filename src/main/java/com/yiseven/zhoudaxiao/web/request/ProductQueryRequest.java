package com.yiseven.zhoudaxiao.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductQueryRequest {

    private Integer pageNum;

    private Integer pageSize;

    private Integer categoryId;

    @NotNull
    private Integer queryType;
}
