package com.yiseven.zhoudaxiao.service;


import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.web.request.LoginRequest;

/**
 * @author hdeng
 */
public interface IndexService {

    Response login(LoginRequest loginRequest);
}
