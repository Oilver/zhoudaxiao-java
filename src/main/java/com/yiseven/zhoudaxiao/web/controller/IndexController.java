package com.yiseven.zhoudaxiao.web.controller;

import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.common.util.RedisUtil;
import com.yiseven.zhoudaxiao.service.IndexService;
import com.yiseven.zhoudaxiao.web.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author hdeng
 */
@RestController
public class IndexController {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    IndexService indexService;

    @PostMapping("login")
    public Response login(@Valid @RequestBody LoginRequest loginRequest) {
        return indexService.login(loginRequest);
    }

    @PostMapping("logout")
    public Response logout(HttpServletRequest request) {
        redisUtil.del(request.getHeader(Const.VALID_HEARER));
        return Response.createBySuccess();
    }
}
