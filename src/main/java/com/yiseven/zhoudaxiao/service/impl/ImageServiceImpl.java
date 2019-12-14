package com.yiseven.zhoudaxiao.service.impl;

import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.common.exception.ExceptionThrow;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.common.response.ResponseCode;
import com.yiseven.zhoudaxiao.common.util.DateUtil;
import com.yiseven.zhoudaxiao.common.util.QCloudUtil;
import com.yiseven.zhoudaxiao.common.util.RedisUtil;
import com.yiseven.zhoudaxiao.entity.ImageEntity;
import com.yiseven.zhoudaxiao.mapper.ext.ImageEntityMapperExt;
import com.yiseven.zhoudaxiao.service.ImageService;
import com.yiseven.zhoudaxiao.web.request.ImageRequest;
import com.yiseven.zhoudaxiao.web.result.ImageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private static final String IMAGE_STYLE = "yyyy-MM-dd-HH:mm:ss";
    private static final int CAROUSEL_NUMBER_MAX = 6;
    private static final String CAROUSEL_CACHE = "carousel_cache";

    @Value("${qcloud.url}")
    private String url;

    @Autowired
    QCloudUtil qCloudUtil;
    @Resource
    ImageEntityMapperExt imageEntityMapperExt;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public Response updateImage(ImageEntity imageEntity) {
        imageEntityMapperExt.updateByPrimaryKeySelective(imageEntity);
        return Response.createBySuccess();
    }

    @Override
    public Boolean deleteImageByProduct(Integer productId) {
        imageEntityMapperExt.deleteImageByProduct(productId);
        return true;
    }

    @Override
    public Response uploadProductImage(MultipartFile multipartFile, Integer productId) throws IOException {
        ExceptionThrow.cast(ResponseCode.DATABASE_ERROR, null == productId);
        String key = Const.PRODUCT_PRE + DateUtil.dateToString(new Date(), IMAGE_STYLE) + "-" + multipartFile.getOriginalFilename();
        //上传图片
        qCloudUtil.upload(multipartFile.getInputStream(), key);
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setProductId(productId);
        imageEntity.setUrl(url + key);
        imageEntity.setBucketKey(key);
        imageEntity.setIsCarousel(0);
        imageEntity.setIsAvatar(0);
        imageEntity.setPriority(0);
        imageEntityMapperExt.insertSelective(imageEntity);
        return Response.createBySuccess(imageEntityMapperExt.queryListByProduct(productId));
    }

    @Override
    public Response deleteImage(Integer id) {
        ImageEntity imageEntity = imageEntityMapperExt.selectByPrimaryKey(id);
        qCloudUtil.deleteImageInQCloud(imageEntity.getBucketKey());
        imageEntityMapperExt.deleteByPrimaryKey(id);
        return Response.createBySuccess("删除图片成功");
    }

    @Override
    public List<ImageEntity> queryListByProduct(Integer productId) {
        return imageEntityMapperExt.queryListByProduct(productId);
    }

    @Override
    public Response uploadImage(MultipartFile multipartFile, String imageType) throws IOException {
        //设定key的前缀
        String prefix = null;
        if (imageType.equals(Const.PRODUCT)) {
            prefix = Const.PRODUCT_PRE;
        } else {
            prefix = "";
        }
        String key = prefix + DateUtil.dateToString(new Date(), IMAGE_STYLE) + "-" + multipartFile.getOriginalFilename();
        //上传图片
        qCloudUtil.upload(multipartFile.getInputStream(), key);
        ImageResult imageResult = new ImageResult();
        imageResult.setKey(key);
        imageResult.setUrl(url + key);
        return Response.createBySuccess(imageResult);
    }

    @Override
    public Boolean addProductImagesAfterUpload(ImageRequest imageRequest) {
        List<String> keys = imageRequest.getKeys();
        ExceptionThrow.cast(ResponseCode.PARAM_WRONG, keys == null || keys.isEmpty());
        List<ImageEntity> imageEntityList = new ArrayList<>();
        if (null == imageRequest.getProductId()) {
            ExceptionThrow.cast(ResponseCode.NULL_EXCEPTION, true);
        }

        for (int i = 0; i < keys.size(); i++) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setProductId(imageRequest.getProductId());
            imageEntity.setUrl(url + keys.get(i));
            imageEntity.setBucketKey(keys.get(i));
            imageEntity.setIsCarousel(0);
            imageEntity.setPriority(keys.size() - i);
            //如果不指定头像的key，默认为第一张图片
            if (i == 0 && StringUtils.isBlank(imageRequest.getAvatarKey())
                    || StringUtils.isNotBlank(imageRequest.getAvatarKey()) && imageRequest.getAvatarKey().equals(keys.get(i))) {
                imageEntity.setIsAvatar(1);
            } else {
                imageEntity.setIsAvatar(0);
            }
            imageEntityList.add(imageEntity);
        }
        imageEntityMapperExt.addImageBatch(imageEntityList);
        log.info("商品id：" + imageRequest.getProductId() + " 的图片成功上传");
        return true;
    }

    /**
     * 查询轮播图列表，并且刷新redis中轮播图的缓存
     *
     * @return
     */
    private List resetCarouselInRedis() {
        List carouselList = imageEntityMapperExt.queryCarousels(CAROUSEL_NUMBER_MAX);
        redisUtil.set(CAROUSEL_CACHE, carouselList, 60 * 60 * 24 * 5);
        return carouselList;
    }

    @Override
    public Response queryCarousels() {
        Object cache = redisUtil.get(CAROUSEL_CACHE);
        if (null != cache) {
            return Response.createBySuccess(cache);
        }
        return Response.createBySuccess(resetCarouselInRedis());
    }

    @Override
    public Response uploadCarousels(MultipartFile multipartFile) throws IOException {
        List list = imageEntityMapperExt.queryCarousels(7);
        if (list.size() >= CAROUSEL_NUMBER_MAX) {
            return Response.createByErrorMessage("轮播图的数量已满6张");
        }
        String key = Const.CAROUSEL_PRE + DateUtil.dateToString(new Date(), IMAGE_STYLE) + "-" + multipartFile.getOriginalFilename();
        //上传图片
        qCloudUtil.upload(multipartFile.getInputStream(), key);
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setUrl(url + key);
        imageEntity.setBucketKey(key);
        imageEntity.setIsCarousel(1);
        imageEntity.setIsAvatar(0);
        imageEntity.setPriority(0);
        imageEntityMapperExt.insertSelective(imageEntity);
        resetCarouselInRedis();
        return Response.createBySuccess("上传轮播图成功，key: " + key);
    }

    @Override
    public Response updateCarousels(ImageEntity imageEntity) {
        updateImage(imageEntity);
        resetCarouselInRedis();
        return Response.createBySuccess();
    }

    @Override
    public Response deleteCarousels(Integer id) {
        deleteImage(id);
        resetCarouselInRedis();
        return Response.createBySuccess();
    }
}