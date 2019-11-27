package com.yiseven.zhoudaxiao.mapper.auto;

import com.yiseven.zhoudaxiao.entity.ImageEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImageEntity record);

    int insertSelective(ImageEntity record);

    ImageEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImageEntity record);

    int updateByPrimaryKey(ImageEntity record);
}