package com.yiseven.zhoudaxiao.service.impl;

import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.common.exception.ExceptionThrow;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.common.response.ResponseCode;
import com.yiseven.zhoudaxiao.common.util.JwtTokenUtil;
import com.yiseven.zhoudaxiao.common.util.MD5Utils;
import com.yiseven.zhoudaxiao.common.util.RedisUtil;
import com.yiseven.zhoudaxiao.entity.PersonEntity;
import com.yiseven.zhoudaxiao.mapper.ext.PersonEntityMapperExt;
import com.yiseven.zhoudaxiao.service.IndexService;
import com.yiseven.zhoudaxiao.web.request.LoginRequest;
import com.yiseven.zhoudaxiao.web.result.AuthResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hdeng
 */
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

    private static final long SEVEN_DAY = 60 * 60 * 24 * 7;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private PersonEntityMapperExt personEntityMapperExt;

    @Override
    public Response login(LoginRequest loginRequest) {
        loginRequest.setPassword(MD5Utils.getMd5Simple(loginRequest.getPassword()));
        PersonEntity personEntity = personEntityMapperExt.queryPerson(loginRequest.getPhone());
        if (personEntity == null) {
            return Response.createByErrorMessage("账户不存在");
        }
        int count = personEntityMapperExt.queryPersonByPassword(loginRequest.getPhone(), loginRequest.getPassword());
        if (0 == count) {
            return Response.createByErrorMessage("密码错误");
        } else if (1 == count) {
            if (Const.ACTIVE_STATUS != personEntity.getStatus()) {
                return Response.createByErrorMessage("该账户还在审核...");
            }
            //生成token
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(personEntity.getPhone(), randomKey);
            //在缓存中保存用户信息，方便获取
            redisUtil.set(token, personEntity, SEVEN_DAY);
            log.info("用户 {} 登录成功", personEntity.getUsername());
            return Response.createBySuccess("登录成功", new AuthResult(token, randomKey));
        } else {
            ExceptionThrow.cast(ResponseCode.PERSON_WRONG, true);
        }
        return Response.createByErrorCode(ResponseCode.COMMON_ERROR);
    }
}
