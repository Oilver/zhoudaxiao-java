package com.yiseven.zhoudaxiao.mapper.ext;

import com.yiseven.zhoudaxiao.mapper.auto.ProductEntityMapper;
import com.yiseven.zhoudaxiao.web.result.ProductResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductEntityMapperExt extends ProductEntityMapper {

    ProductResult query(Integer id);

    List<ProductResult> queryProductList(@Param("categoryId") Integer categoryId,
                                         @Param("isNew") Boolean isNew,
                                         @Param("productName") String productName,
                                         @Param("orderBySortType") String orderBySortType);

    int checkIsExist(String name);

    /**
     * 将一批商品置位其他类别
     */
    void updateCategoryBatch(@Param("categoryId") Integer categoryId, @Param("ids") List<Integer> ids);
}