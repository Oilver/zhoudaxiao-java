package com.yiseven.zhoudaxiao.web.request;


import com.yiseven.zhoudaxiao.common.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hdeng
 */
@Data
public class PersonRequest extends BaseRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private Integer role;
}