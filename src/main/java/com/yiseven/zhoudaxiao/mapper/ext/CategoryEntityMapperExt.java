package com.yiseven.zhoudaxiao.mapper.ext;


import com.yiseven.zhoudaxiao.entity.CategoryEntity;
import com.yiseven.zhoudaxiao.mapper.auto.CategoryEntityMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryEntityMapperExt extends CategoryEntityMapper {

    List<CategoryEntity> queryAll();

}