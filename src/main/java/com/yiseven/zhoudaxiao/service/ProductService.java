package com.yiseven.zhoudaxiao.service;

import com.yiseven.zhoudaxiao.web.request.ProductInsertRequest;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.web.request.ProductQueryRequest;
import com.yiseven.zhoudaxiao.web.request.ProductUpdateRequest;
import com.yiseven.zhoudaxiao.web.result.ProductResult;

public interface ProductService {

    Response deleteProduct(int id);

    Response updateProduct(ProductUpdateRequest productUpdateRequest, String token);

    Response addProduct(ProductInsertRequest productInsertRequest, String token);

    Response<ProductResult> queryProduct(int id);

    Response queryProductList(ProductQueryRequest productQueryRequest);

    Response checkIsExist(String name);
}
