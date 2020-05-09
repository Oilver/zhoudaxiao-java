package com.yiseven.zhoudaxiao.service.impl;

import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.common.exception.ExceptionThrow;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.common.response.ResponseCode;
import com.yiseven.zhoudaxiao.common.util.MD5Utils;
import com.yiseven.zhoudaxiao.common.util.RedisUtil;
import com.yiseven.zhoudaxiao.entity.PersonEntity;
import com.yiseven.zhoudaxiao.mapper.ext.PersonEntityMapperExt;
import com.yiseven.zhoudaxiao.service.PersonService;
import com.yiseven.zhoudaxiao.web.request.PersonRequest;
import com.yiseven.zhoudaxiao.web.result.PersonListResult;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author hdeng
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Autowired
    RedisUtil redisUtil;
    @Resource
    PersonEntityMapperExt personEntityMapperExt;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Response addPerson(PersonRequest personRequest) {
        //1.是否已注册
        PersonEntity queryResult = personEntityMapperExt.queryPerson(personRequest.getPhone());
        if (queryResult != null) {
            return Response.createByErrorMessage("该手机已经被注册");
        }
        PersonEntity personEntity = new PersonEntity();
        modelMapper.map(personRequest, personEntity);
        personEntity.setStatus(Const.REVIEW_STATUS);
        personEntity.setCreateDate(new Date());
        personEntity.setLastUpdateDate(new Date());
        personEntity.setPassword(MD5Utils.getMd5Simple(personEntity.getPassword()));
        int result = personEntityMapperExt.insertSelective(personEntity);
        if (Const.INSERT_ONE == result) {
            log.info("用户 {} 注册成功", personEntity.getUsername() + " " + personEntity.getPhone());
            return Response.createBySuccess();
        }
        return Response.createByErrorMessage("用户注册失败");
    }

    @Override
    public Response updatePerson(PersonEntity personEntity) {
        int resultCount = personEntityMapperExt.updateByPrimaryKeySelective(personEntity);
        ExceptionThrow.cast(ResponseCode.DATABASE_ERROR, 1 != resultCount);
        return Response.createBySuccess();
    }

    @Override
    public Response queryPerson(String phone) {
        PersonEntity personEntity = personEntityMapperExt.queryPerson(phone);
        ExceptionThrow.cast(ResponseCode.RESULT_NULL, null == personEntity);
        return Response.createBySuccess(personEntity);
    }

    @Override
    public PersonEntity queryCurrentPerson(String token) {
        // TODO: 2020/5/9  
        return (PersonEntity) redisUtil.get(token);
    }

    @Override
    public Response queryPersonList() {
        PersonListResult personListResult = new PersonListResult();
        personListResult.setPersonList(personEntityMapperExt.queryPersonList(Const.ACTIVE_STATUS));
        personListResult.setUnPassList(personEntityMapperExt.queryPersonList(Const.REVIEW_STATUS));
        return Response.createBySuccess(personListResult);
    }

    @Override
    public Response delete(int id) {
        ExceptionThrow.cast(ResponseCode.DATABASE_ERROR, 1 != personEntityMapperExt.deleteByPrimaryKey(id));
        return Response.createBySuccess();
    }
}
