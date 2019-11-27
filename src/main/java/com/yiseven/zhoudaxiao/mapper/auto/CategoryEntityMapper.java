package com.yiseven.zhoudaxiao.mapper.auto;

import com.yiseven.zhoudaxiao.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CategoryEntity record);

    int insertSelective(CategoryEntity record);

    CategoryEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CategoryEntity record);

    int updateByPrimaryKey(CategoryEntity record);
}