package com.yiseven.zhoudaxiao.common.config;

import com.yiseven.zhoudaxiao.common.util.QCloudUtil;
import com.yiseven.zhoudaxiao.entity.ImageEntity;
import com.yiseven.zhoudaxiao.mapper.ext.ImageEntityMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class ScheduledConfig {

    @Autowired
    QCloudUtil qCloudUtil;

    @Resource
    ImageEntityMapperExt imageEntityMapperExt;

    /**
     * 设置定时任务删除云存储中冗余的图片（新增商品时上传了图片，但是放弃点击确认按钮导致的垃圾数据）
     * 项目启动后的30秒后执行，每隔5天执行一次
     */
    @Scheduled(initialDelay = 30000, fixedDelay = 60000 * 60 * 24 * 5)
    public void deleteInvalidImageInCloud() {
        log.info("定期删除垃圾数据 -- 定时任务启动...");
        List<ImageEntity> imageEntityList = imageEntityMapperExt.queryAll();
        List<Integer> deleteIds = new ArrayList<>();
        List<String> imageBucketKeys = new ArrayList<>();
        //1.先查询数据库是否有垃圾数据，有就先删除
        imageEntityList.forEach(item -> {
            imageBucketKeys.add(item.getBucketKey());
            if (!item.getIsCarousel() && null == item.getProductId()) {
                deleteIds.add(item.getId());
            }
        });
        if (!deleteIds.isEmpty()) {
            imageEntityMapperExt.deleteBatch(deleteIds);
        }
        //2.查询云中的所有图片的key，和数据库中的对比，没在数据库中存储的，就是垃圾数据，要删除
        List<String> bucketKeys = qCloudUtil.queryAllBucketKeys();
        List<String> deleteBucketKeys = new ArrayList<>();
        bucketKeys.forEach(item -> {
            if (!imageBucketKeys.contains(item)) {
                deleteBucketKeys.add(item);
            }
        });
        if (!deleteBucketKeys.isEmpty()) {
            qCloudUtil.deleteImageBatchInQCloud(deleteBucketKeys);
        }
    }
}
