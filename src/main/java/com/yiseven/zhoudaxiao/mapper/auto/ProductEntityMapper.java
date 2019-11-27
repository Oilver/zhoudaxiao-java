package com.yiseven.zhoudaxiao.mapper.auto;

import com.yiseven.zhoudaxiao.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductEntity record);

    int insertSelective(ProductEntity record);

    ProductEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductEntity record);
}