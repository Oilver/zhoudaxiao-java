package com.yiseven.zhoudaxiao.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.common.response.ResponseCode;
import com.yiseven.zhoudaxiao.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author hdeng
 */
@Component
@Slf4j
public class LimitRequestInterceptor implements HandlerInterceptor {
    RateLimiter rateLimiter = RateLimiter.create(50);

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //1.先判断是否有令牌
        if (!rateLimiter.tryAcquire()) {
            rejectAccess(response);
            return false;
        }
        //2.再判断是否该ip是否有权继续访问
        String ip = getIpAddress(request);

        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(ip, redisTemplate.getConnectionFactory());
        int increment = redisAtomicInteger.getAndIncrement();
        if (increment == 0) {//初始设置过期时间, 同一IP1秒内最多发起10次请求
            redisAtomicInteger.expire(1, TimeUnit.SECONDS);
            return true;
        } else if (increment >= 10) {
            rejectAccess(response);
            return false;
        } else {
            return true;
        }
    }

    private void rejectAccess(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        response.getWriter().print(JSON.toJSONString(Response.createByErrorCode(ResponseCode.REJECT_ACCESS)));
    }

    /**
     * 防止有代理会拿不到真实ip
     *
     * @param request
     * @return
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}