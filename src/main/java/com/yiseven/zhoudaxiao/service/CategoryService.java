package com.yiseven.zhoudaxiao.service;

import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.entity.CategoryEntity;

/**
 * @author hdeng
 */
public interface CategoryService {

    Response queryAll();

    Response addCategory(CategoryEntity categoryEntity);

    Response updateCategory(CategoryEntity categoryEntity);

    /**
     * 删除某分类前先把该分类下的所有商品都放在“其他”里
     *
     * @param id
     * @return
     */
    Response deleteCategory(int id);

}
