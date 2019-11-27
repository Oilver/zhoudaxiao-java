package com.yiseven.zhoudaxiao.mapper.ext;

import com.yiseven.zhoudaxiao.entity.ImageEntity;
import com.yiseven.zhoudaxiao.mapper.auto.ImageEntityMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ImageEntityMapperExt extends ImageEntityMapper {

    void deleteImageByProduct(Integer productId);

    List<ImageEntity> queryCarousels(int max);

    void addImageBatch(List<ImageEntity> list);

    List<ImageEntity> queryListByProduct(Integer productId);
}