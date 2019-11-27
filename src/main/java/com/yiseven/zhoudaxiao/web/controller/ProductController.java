package com.yiseven.zhoudaxiao.web.controller;

import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.web.request.ProductInsertRequest;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.service.ProductService;
import com.yiseven.zhoudaxiao.web.request.ProductQueryRequest;
import com.yiseven.zhoudaxiao.web.request.ProductUpdateRequest;
import com.yiseven.zhoudaxiao.web.result.ProductResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author hdeng
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("delete")
    public Response deleteProduct(@RequestParam int id) {
        return productService.deleteProduct(id);
    }

    @PostMapping("update")
    public Response updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest, HttpServletRequest request) {
        return productService.updateProduct(productUpdateRequest, request.getHeader(Const.VALID_HEARER));
    }

    @ApiOperation("添加商品，包括商品的图片的key")
    @PostMapping("add")
    public Response addProduct(@Valid @RequestBody ProductInsertRequest productInsertRequest, HttpServletRequest request) {
        return productService.addProduct(productInsertRequest, request.getHeader(Const.VALID_HEARER));
    }

    @ApiOperation("查询商品详情")
    @PostMapping("query")
    public Response<ProductResult> queryProduct(@RequestParam int id) {
        return productService.queryProduct(id);
    }

    @ApiOperation("查询商品列表")
    @PostMapping("queryProductList")
    public Response queryProductList(@Valid @RequestBody ProductQueryRequest productQueryRequest) {
        return productService.queryProductList(productQueryRequest);
    }

    @PostMapping("checkIsExist")
    public Response checkIsExist(@RequestParam String name) {
        return productService.checkIsExist(name);
    }
}
