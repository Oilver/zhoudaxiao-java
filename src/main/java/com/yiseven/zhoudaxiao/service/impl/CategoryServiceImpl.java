package com.yiseven.zhoudaxiao.service.impl;

import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.entity.CategoryEntity;
import com.yiseven.zhoudaxiao.mapper.ext.CategoryEntityMapperExt;
import com.yiseven.zhoudaxiao.mapper.ext.ProductEntityMapperExt;
import com.yiseven.zhoudaxiao.service.CategoryService;
import com.yiseven.zhoudaxiao.web.result.ProductResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryEntityMapperExt categoryEntityMapperExt;

    @Resource
    ProductEntityMapperExt productEntityMapperExt;

    @Override
    public Response queryAll() {
        return Response.createBySuccess(categoryEntityMapperExt.queryAll());
    }

    @Override
    public Response addCategory(CategoryEntity categoryEntity) {
        categoryEntityMapperExt.insertSelective(categoryEntity);
        return Response.createBySuccess();
    }

    @Override
    public Response updateCategory(CategoryEntity categoryEntity) {
        categoryEntityMapperExt.updateByPrimaryKeySelective(categoryEntity);
        return Response.createBySuccess();
    }

    @Override
    @Transactional
    public Response deleteCategory(int id) {
        List<ProductResult> productResults = productEntityMapperExt.queryProductList(id, null, null);
        List<Integer> productIds = new ArrayList<>();
        productResults.forEach(item -> productIds.add(item.getId()));
        //删除某分类前先把该分类下的所有商品都放在“其他”里
        if (!productIds.isEmpty()) {
            productEntityMapperExt.updateCategoryBatch(Const.OTHER_CATEGORY_ID, productIds);
            log.info("商品的分类被更改为其他，ids： {}", productIds);
        }
        categoryEntityMapperExt.deleteByPrimaryKey(id);
        return Response.createBySuccess();
    }
}
