package com.yiseven.zhoudaxiao.web.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yiseven.zhoudaxiao.entity.ImageEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductResult {
    private Integer id;

    private String name;

    private Integer categoryId;

    private String categoryName;

    private Integer brandId;

    private String introduction;

    private Double originalPrice;

    private Double discountPrice;

    private Double agentPrice;

    private Double secondAgentPrice;

    private Integer priority;

    private Integer pageviews;

    private Integer like;

    private Integer totalSales;

    private Integer monthlySales;

    private Double freight;

    private Integer stock;

    private Integer isNew;

    private String detail;

    /**
     * 商品的头像的key
     */
    private String avatarUrl;

    /**
     * 商品的全部图片的实体
     */
    private List<ImageEntity> imageEntityList;


    private String createBy;

    private String lastUpdateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateTime;

}
