package com.yiseven.zhoudaxiao.mapper.auto;

import com.yiseven.zhoudaxiao.entity.PersonEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonEntity record);

    int insertSelective(PersonEntity record);

    PersonEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonEntity record);

    int updateByPrimaryKey(PersonEntity record);
}