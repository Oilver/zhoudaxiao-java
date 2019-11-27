package com.yiseven.zhoudaxiao.mapper.ext;

import com.yiseven.zhoudaxiao.mapper.auto.ProductEntityMapper;
import com.yiseven.zhoudaxiao.web.result.ProductResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductEntityMapperExt extends ProductEntityMapper {

    ProductResult query(Integer id);

    /**
     * 今日推荐
     *
     * @return
     */
    List<ProductResult> queryTodayList();

    /**
     * 爆款推荐
     *
     * @return
     */
    List<ProductResult> queryHotList();


    List<ProductResult> queryListByCategory(Integer categoryId);

    int checkIsExist(String name);

}