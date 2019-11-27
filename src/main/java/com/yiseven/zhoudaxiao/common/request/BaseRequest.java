package com.yiseven.zhoudaxiao.common.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author hdeng
 */
@Data
public class BaseRequest {
    @JsonIgnore
    private Integer id;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date lastUpdateTime;
}
