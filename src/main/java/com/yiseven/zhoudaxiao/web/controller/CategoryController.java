package com.yiseven.zhoudaxiao.web.controller;

import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.entity.CategoryEntity;
import com.yiseven.zhoudaxiao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("queryAll")
    public Response queryAll() {
        return categoryService.queryAll();
    }

    @PostMapping("add")
    public Response addCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.addCategory(categoryEntity);
    }

    @PostMapping("update")
    public Response updateCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.updateCategory(categoryEntity);
    }

    @PostMapping("delete")
    public Response deleteCategory(Integer id) {
        return categoryService.deleteCategory(id);
    }

}
