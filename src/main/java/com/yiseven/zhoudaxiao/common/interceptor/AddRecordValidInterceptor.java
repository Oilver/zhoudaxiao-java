package com.yiseven.zhoudaxiao.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.common.response.ResponseCode;
import com.yiseven.zhoudaxiao.common.util.RedisUtil;
import com.yiseven.zhoudaxiao.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hdeng
 * 只有管理员才有权利修改record
 */
@Component
public class AddRecordValidInterceptor implements HandlerInterceptor {
    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        PersonEntity personEntity = (PersonEntity) redisUtil.get(request.getHeader(Const.ZHOUDAXIAO_AUTH));
        if (personEntity.getRole() == Const.ADMIN) {
            return true;
        }
        noRight(response);
        return false;
    }

    private void noRight(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        response.getWriter().print(JSON.toJSONString(Response.createByErrorCode(ResponseCode.NO_POWER)));
    }

}