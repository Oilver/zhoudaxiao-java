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

    Response deleteCategory(int id);

}
