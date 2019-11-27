package com.yiseven.zhoudaxiao.web.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yiseven.zhoudaxiao.common.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hdeng
 */
@Data
public class ProductUpdateRequest extends BaseRequest {

    private Integer id;

    private String name;

    private Integer categoryId;

    private Double agentPrice;

    private Double originalPrice;

    private Double discountPrice;

    /**
     * 优先级（用于首页今日推荐模块排序
     */
    private Integer priority;

    private Integer pageviews;

    private Double freight;

    private Integer stock;

    private Integer isNew;

    private String introduction;

    private String detail;

    @JsonIgnore
    private String lastUpdateBy;

    //    private Integer brandId;
    //    private Double secondAgentPrice;
    //    private Integer like;
}
