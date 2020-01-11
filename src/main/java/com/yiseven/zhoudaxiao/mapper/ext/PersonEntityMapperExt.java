package com.yiseven.zhoudaxiao.mapper.ext;

import com.yiseven.zhoudaxiao.entity.PersonEntity;
import com.yiseven.zhoudaxiao.mapper.auto.PersonEntityMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hdeng
 */
@Mapper
public interface PersonEntityMapperExt extends PersonEntityMapper {

    PersonEntity queryPerson(String phone);

    List<PersonEntity> queryPersonList(int status);

    int queryPersonByPassword(@Param("phone") String phone, @Param("password") String password);
}