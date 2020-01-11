package com.yiseven.zhoudaxiao.web.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yiseven.zhoudaxiao.common.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author hdeng
 */
@Data
public class ProductInsertRequest extends BaseRequest {

    @NotNull
    private String name;

    private Integer categoryId;

    @NotNull
    private BigDecimal agentPrice;

    @NotNull
    private BigDecimal originalPrice;

    @NotNull
    private BigDecimal discountPrice;

    /**
     * 优先级（用于首页今日推荐模块排序）
     */
    private Integer priority;

    private Integer pageviews;

    private BigDecimal freight;

    private Integer stock;

    private Boolean isNew;

    private String introduction;

    private String detail;

    /**
     * 商品的头像的key
     */
    private String avatarKey;

    /**
     * 商品的全部图片的key
     */
    private List<String> keys;

    @JsonIgnore
    private String createBy;
    @JsonIgnore
    private String lastUpdateBy;

    //    private Integer brandId;
    //    private Double secondAgentPrice;
    //    private Integer totalSales;
    //    private Integer monthlySales;

    //    private Integer like;
}
