package com.yiseven.zhoudaxiao.service.impl;

import com.yiseven.zhoudaxiao.common.util.RedisUtil;
import com.yiseven.zhoudaxiao.entity.UserEntity;
import com.yiseven.zhoudaxiao.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public UserEntity getUserFromRedisByToken(String token) {
        Object object = redisUtil.get(token);
        if (object == null) {
            return null;
        }
        return (UserEntity) object;
    }
}
