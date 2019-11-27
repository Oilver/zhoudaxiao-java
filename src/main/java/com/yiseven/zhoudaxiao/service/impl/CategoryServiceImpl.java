package com.yiseven.zhoudaxiao.service.impl;

import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.entity.CategoryEntity;
import com.yiseven.zhoudaxiao.mapper.ext.CategoryEntityMapperExt;
import com.yiseven.zhoudaxiao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryEntityMapperExt categoryEntityMapperExt;

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
    public Response deleteCategory(int id) {
        categoryEntityMapperExt.deleteByPrimaryKey(id);
        return Response.createBySuccess();
    }
}
