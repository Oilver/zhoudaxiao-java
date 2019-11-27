package com.yiseven.zhoudaxiao.mapper.auto;

import com.yiseven.zhoudaxiao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
}