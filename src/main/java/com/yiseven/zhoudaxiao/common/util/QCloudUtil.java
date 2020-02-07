package com.yiseven.zhoudaxiao.common.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.DeleteObjectsRequest;
import com.qcloud.cos.model.ObjectListing;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class QCloudUtil {

    @Value("${qcloud.secretId}")
    private String secretId;
    @Value("${qcloud.secretKey}")
    private String secretKey;
    @Value("${qcloud.region}")
    private String regionName;
    @Value("${qcloud.bucketName}")
    private String bucketName;
    @Value("${qcloud.url}")
    private String url;

    @Autowired
    COSClient cosClient;

    @Bean
    public COSClient cosClient() {
        return new COSClient(new BasicCOSCredentials(secretId, secretKey), new ClientConfig(new Region(regionName)));
    }

    public void upload(InputStream inputStream, String key) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, new ObjectMetadata());
        cosClient.putObject(putObjectRequest);
        log.info("文件上传成功，key： {}", key);
    }

    public void deleteImageInQCloud(String key) {
        cosClient.deleteObject(bucketName, key);
        log.info("文件删除成功，key： {}", key);
    }

    public void deleteImageBatchInQCloud(List<String> keys) {
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
        List<DeleteObjectsRequest.KeyVersion> keyVersions = new ArrayList<>();
        keys.forEach(item -> keyVersions.add(new DeleteObjectsRequest.KeyVersion(item)));
        deleteObjectsRequest.setKeys(keyVersions);
        cosClient.deleteObjects(deleteObjectsRequest);
        log.info("文件批量删除成功，keys： {}", keys);
    }

    /**
     * 查询除了文件夹为special的所有文件的key
     *
     * @return
     */
    public List<String> queryAllBucketKeys() {
        ObjectListing objectListing = cosClient.listObjects(bucketName);
        List<String> bucketKeys = new ArrayList<>();
        for (COSObjectSummary cosObjectSummary : objectListing.getObjectSummaries()) {
            if (!cosObjectSummary.getKey().contains("special/")) {
                bucketKeys.add(cosObjectSummary.getKey());
            }
        }
        return bucketKeys;
    }
}
