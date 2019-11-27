package com.yiseven.zhoudaxiao.service;

import com.yiseven.zhoudaxiao.entity.UserEntity;

/**
 * 公有的服务
 */
public interface CommonService {

    UserEntity getUserFromRedisByToken(String token);
}
