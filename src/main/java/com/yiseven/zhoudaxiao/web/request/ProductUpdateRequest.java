package com.yiseven.zhoudaxiao.web.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yiseven.zhoudaxiao.common.request.BaseRequest;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hdeng
 */
@Data
public class ProductUpdateRequest extends BaseRequest {

    private Integer id;

    private String name;

    private Integer categoryId;

    private BigDecimal agentPrice;

    private BigDecimal originalPrice;

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

    @JsonIgnore
    private String lastUpdateBy;

    //    private Integer brandId;
    //    private Double secondAgentPrice;
    //    private Integer like;
}
