package com.yiseven.zhoudaxiao.mapper.ext;

import com.yiseven.zhoudaxiao.entity.UserEntity;
import com.yiseven.zhoudaxiao.mapper.auto.UserEntityMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hdeng
 */
@Mapper
public interface UserEntityMapperExt extends UserEntityMapper {

    UserEntity queryUser(String phone);

    List<UserEntity> queryUserList(int status);

    int queryUserByPassword(@Param("phone") String phone, @Param("password") String password);
}