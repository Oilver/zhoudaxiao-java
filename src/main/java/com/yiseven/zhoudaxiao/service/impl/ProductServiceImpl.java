package com.yiseven.zhoudaxiao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiseven.zhoudaxiao.common.Const.Const;
import com.yiseven.zhoudaxiao.common.enums.QueryTypeEnum;
import com.yiseven.zhoudaxiao.common.exception.ExceptionThrow;
import com.yiseven.zhoudaxiao.common.response.ResponseCode;
import com.yiseven.zhoudaxiao.common.util.QCloudUtil;
import com.yiseven.zhoudaxiao.entity.ImageEntity;
import com.yiseven.zhoudaxiao.entity.UserEntity;
import com.yiseven.zhoudaxiao.service.CommonService;
import com.yiseven.zhoudaxiao.service.ImageService;
import com.yiseven.zhoudaxiao.service.UserService;
import com.yiseven.zhoudaxiao.web.request.ImageRequest;
import com.yiseven.zhoudaxiao.web.request.ProductInsertRequest;
import com.yiseven.zhoudaxiao.common.response.Response;
import com.yiseven.zhoudaxiao.entity.ProductEntity;
import com.yiseven.zhoudaxiao.mapper.ext.ProductEntityMapperExt;
import com.yiseven.zhoudaxiao.service.ProductService;
import com.yiseven.zhoudaxiao.web.request.ProductQueryRequest;
import com.yiseven.zhoudaxiao.web.request.ProductUpdateRequest;
import com.yiseven.zhoudaxiao.web.result.ProductResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    CommonService commonService;
    @Autowired
    ModelMapper modelMapper;
    @Resource
    ProductEntityMapperExt productEntityMapperExt;
    @Autowired
    ImageService imageService;
    @Autowired
    ExecutorService executorPool;
    @Autowired
    QCloudUtil qCloudUtil;
    @Autowired
    UserService userService;

    @Override
    @Transactional
    public Response deleteProduct(int id) {
        List<ImageEntity> list = imageService.queryListByProduct(id);
        List<String> keys = new ArrayList<>();
        for (ImageEntity imageEntity : list) {
            keys.add(imageEntity.getBucketKey());
        }
        productEntityMapperExt.deleteByPrimaryKey(id);
        imageService.deleteImageByProduct(id);
        executorPool.execute(() -> {
            for (String key : keys) {
                qCloudUtil.deleteImageInQCloud(key);
                log.info("文件删除成功,key: " + key);
            }
        });
        log.info("删除商品成功，id：{}", id);
        return Response.createBySuccess("删除商品成功，id：" + id);
    }

    @Override
    @Transactional
    public Response updateProduct(ProductUpdateRequest productUpdateRequest, String token) {
        productUpdateRequest.setLastUpdateTime(new Date());
        UserEntity user = userService.queryCurrentUser(token);
        productUpdateRequest.setLastUpdateBy(user.getUsername());

        ProductEntity productEntity = new ProductEntity();
        modelMapper.map(productUpdateRequest, productEntity);
        int resultCount = productEntityMapperExt.updateByPrimaryKeySelective(productEntity);
        ExceptionThrow.cast(ResponseCode.DATABASE_ERROR, 1 != resultCount);
        //查看头像是否有变化
        List<ImageEntity> imageEntityList = imageService.queryListByProduct(productUpdateRequest.getId());
        ExceptionThrow.cast(ResponseCode.PRODUCT_ERROR, imageEntityList.isEmpty());
        boolean hasAvatar = false;
        for (ImageEntity imageEntity : imageEntityList) {
            if (imageEntity.getIsAvatar() == 1) {
                hasAvatar = true;
                break;
            }
        }
        if (!hasAvatar) {
            imageEntityList.get(0).setIsAvatar(1);
            imageService.updateImage(imageEntityList.get(0));
            log.info("商品的头像已更换，id：{}, imageId：{}", productEntity.getId(), imageEntityList.get(0).getId());
        }
        log.info("商品更新成功，id：{}，name：{}", productEntity.getId(), productEntity.getName());
        return Response.createBySuccess("商品更新成功，id：" + productEntity.getId());
    }

    @Override
    @Transactional
    public Response addProduct(ProductInsertRequest productInsertRequest, String token) {
        if (null == productInsertRequest.getCategoryId()) {
            productInsertRequest.setCategoryId(Const.OTHER_CATEGORY_ID);
        }
        if (null == productInsertRequest.getPriority()) {
            productInsertRequest.setPriority(0);
        }
        productInsertRequest.setPageviews(0);
        productInsertRequest.setCreateTime(new Date());
        productInsertRequest.setLastUpdateTime(new Date());
        UserEntity user = userService.queryCurrentUser(token);
        productInsertRequest.setCreateBy(user.getUsername());
        productInsertRequest.setLastUpdateBy(user.getUsername());

        ProductEntity productEntity = new ProductEntity();
        modelMapper.map(productInsertRequest, productEntity);
        //1.先插入数据
        int resultCount = productEntityMapperExt.insertSelective(productEntity);
        ExceptionThrow.cast(ResponseCode.DATABASE_ERROR, 1 != resultCount);
        //2.把商品的图片的插入image表
        ImageRequest imageRequest = new ImageRequest();
        modelMapper.map(productInsertRequest, imageRequest);
        imageRequest.setProductId(productEntity.getId());
        imageService.addProductImagesAfterUpload(imageRequest);
        log.info("商品添加成功，id：{}，name：{}", productEntity.getId(), productEntity.getName());
        return Response.createBySuccess("商品添加成功，id：" + productEntity.getId());
    }

    @Override
    public Response<ProductResult> queryProduct(int id) {
        ProductResult productResult = productEntityMapperExt.query(id);
        if (null == productResult) {
            return Response.createBySuccessMessage("查询不到id: " + id + "的商品");
        }
        //空值处理
        productResult.setOriginalPrice(productResult.getOriginalPrice() == null ? 0 : productResult.getOriginalPrice());
        productResult.setDiscountPrice(productResult.getDiscountPrice() == null ? 0 : productResult.getDiscountPrice());
        productResult.setPageviews(productResult.getPageviews() == null ? 1 : productResult.getPageviews() + 1);
        //启动线程给浏览量加1
        executorPool.execute(() -> {
            ProductEntity entity = new ProductEntity();
            entity.setId(id);
            entity.setPageviews(productResult.getPageviews());
            productEntityMapperExt.updateByPrimaryKeySelective(entity);
        });

        //查询图片
        List<ImageEntity> imageEntityList = imageService.queryListByProduct(id);
        productResult.setImageEntityList(imageEntityList);
        for (ImageEntity imageEntity : imageEntityList) {
            if (1 == imageEntity.getIsAvatar()) {
                productResult.setAvatarUrl(imageEntity.getUrl());
                break;
            }
        }
        return Response.createBySuccess(productResult);
    }

    @Override
    public Response queryProductList(ProductQueryRequest productQueryRequest) {
        //参数空值处理,默认为第1页，6条，pageviews desc排序
        PageHelper.startPage(null == productQueryRequest.getPageNum() ? Const.PAGE_NUM_DEFAULT : productQueryRequest.getPageNum(),
                null == productQueryRequest.getPageSize() ? Const.PAGE_SIZE_DEFAULT : productQueryRequest.getPageSize());
        String orderBy = StringUtils.isBlank(productQueryRequest.getOrderBy()) ? Const.ORDER_BY_DEFAULT : productQueryRequest.getOrderBy();
        String sortType = StringUtils.isBlank(productQueryRequest.getSortType()) ? Const.SORT_TYPE_DEFAULT : productQueryRequest.getSortType();
        //So
        String orderBySortType = orderBy + " " + sortType;
        List<ProductResult> list = productEntityMapperExt.queryProductList(productQueryRequest.getCategoryId(), productQueryRequest.getIsNew(), orderBySortType);
        PageInfo<ProductResult> pageInfo = new PageInfo<>(list);
        return Response.createBySuccess(pageInfo);
    }

    @Override
    public Response checkIsExist(String name) {
        int count = productEntityMapperExt.checkIsExist(name);
        if (count > 0) {
            return Response.createBySuccess(true);
        }
        return Response.createBySuccess(false);
    }
}
