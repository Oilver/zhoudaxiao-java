package com.yiseven.zhoudaxiao.web.controller;

import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.entity.ImageEntity;
import com.yiseven.zhoudaxiao.service.ImageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author hdeng
 */
@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("update")
    public Response updateImage(@RequestBody ImageEntity imageEntity) {
        return imageService.updateImage(imageEntity);
    }

    @PostMapping("deleteImage")
    public Response deleteImage(@RequestParam Integer id) {
        return imageService.deleteImage(id);
    }


    @ApiOperation("上传某个商品的的图片（该商品已在库中）")
    @PostMapping("uploadProductImage")
    public Response uploadProductImage(@RequestParam("file") MultipartFile multipartFile, @RequestParam Integer productId) throws IOException {
//        Integer productId = Integer.valueOf(request.getHeader("productId"));
        return imageService.uploadProductImage(multipartFile, productId);
    }

    @ApiOperation("查询某个商品的所有图片")
    @PostMapping("queryListByProduct")
    public Response queryListByProduct(@RequestParam Integer productId) {
        return Response.createBySuccess(imageService.queryListByProduct(productId));
    }

    @ApiOperation("上传单个图片（新增商品时调用的接口，此时商品还未入库）")
    @PostMapping("uploadImage")
    public Response uploadImage(@RequestParam("file") MultipartFile multipartFile, @RequestParam String imageType) throws IOException {
        return imageService.uploadImage(multipartFile, imageType);
    }


    @ApiOperation("上传首页的轮播图")
    @PostMapping("uploadCarousels")
    public Response uploadCarousels(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return imageService.uploadCarousels(multipartFile);
    }

    @ApiOperation("查询首页的轮播图")
    @PostMapping("queryCarousels")
    public Response queryCarousels() {
        return imageService.queryCarousels();
    }

    @ApiOperation("删除轮播图")
    @PostMapping("deleteCarousels")
    public Response deleteCarousels(@RequestParam Integer id) {
        return imageService.deleteCarousels(id);
    }

    @ApiOperation("更新轮播图的优先级")
    @PostMapping("updateCarousels")
    public Response updateCarousels(@RequestBody ImageEntity imageEntity) {
        return imageService.updateCarousels(imageEntity);
    }
}
