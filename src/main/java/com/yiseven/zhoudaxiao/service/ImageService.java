package com.yiseven.zhoudaxiao.service;

import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.entity.ImageEntity;
import com.yiseven.zhoudaxiao.web.request.ImageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author hdeng
 */
public interface ImageService {
    Response deleteImage(Integer id);

    Response updateImage(ImageEntity imageEntity);

    /**
     * 删除某product的所有images
     *
     * @param productId
     * @return
     */
    Boolean deleteImageByProduct(Integer productId);

    /**
     * 根据productId查找所有图片
     *
     * @param productId
     * @return
     */
    List<ImageEntity> queryListByProduct(Integer productId);

    /**
     * 上传单个图片
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    Response uploadImage(MultipartFile multipartFile, String imageType) throws IOException;

    Response uploadProductImage(MultipartFile multipartFile, Integer productId) throws IOException;

    /**
     * 将商品的的图片记录在数据库
     *
     * @param imageRequest
     * @return
     */
    Boolean addProductImagesAfterUpload(ImageRequest imageRequest);

    Response queryCarousels();

    Response uploadCarousels(MultipartFile multipartFile) throws IOException;

    Response updateCarousels(ImageEntity imageEntity);

    Response deleteCarousels(Integer id);
}
