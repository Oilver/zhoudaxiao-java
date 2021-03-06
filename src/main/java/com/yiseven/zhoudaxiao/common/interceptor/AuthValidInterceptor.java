package com.yiseven.zhoudaxiao.common.interceptor;

import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.common.response.ResponseCode;
import com.yiseven.zhoudaxiao.common.util.JwtTokenUtil;
import com.yiseven.zhoudaxiao.common.util.ToolUtil;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hdeng
 */
@Component
@Slf4j
public class AuthValidInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if ("OPTIONS".equals(request.getMethod())) {
            //预检则不校验
            return false;
        }

        final String origin = request.getHeader("request-origin");
        //公用查询接口并且来源不是angular的直接通过，web端的所有请求都要校验（除了swagger和login）
        if (!"angular".equalsIgnoreCase(origin) && WebSecurityConfig.EXCLUDE_APIS.contains(request.getServletPath())) {
            return true;
        }

        final String authToken = request.getHeader(Const.ZHOUDAXIAO_AUTH);
        if (null == authToken) {
            //缺少header
            needHeader(response);
            return false;
        }
        //验证token是否过期,包含了验证jwt是否正确
        try {
            if (jwtTokenUtil.isTokenExpired(authToken)) {
                needLogin(response);
                return false;
            } else {
                return true;
            }
        } catch (JwtException e) {
            //有异常就是token解析失败
            needLogin(response);
            return false;
        }
    }


    private void needLogin(HttpServletResponse response) throws IOException {
        log.error("未登录");
        ToolUtil.writeServletResponseJson(response, ResponseCode.NEED_LOGIN);
    }

    private void needHeader(HttpServletResponse response) throws IOException {
        log.error("缺少token头部");
        ToolUtil.writeServletResponseJson(response, ResponseCode.HEADER_LACK);
    }
}