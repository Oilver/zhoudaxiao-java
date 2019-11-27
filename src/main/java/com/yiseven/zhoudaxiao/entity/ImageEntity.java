package com.yiseven.zhoudaxiao.entity;

public class ImageEntity {
    private Integer id;

    private Integer productId;

    private String url;

    private String bucketKey;

    private Integer isCarousel;

    private Integer isAvatar;

    private Integer priority;

    public String getBucketKey() {
        return bucketKey;
    }

    public void setBucketKey(String bucketKey) {
        this.bucketKey = bucketKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIsCarousel() {
        return isCarousel;
    }

    public void setIsCarousel(Integer isCarousel) {
        this.isCarousel = isCarousel;
    }

    public Integer getIsAvatar() {
        return isAvatar;
    }

    public void setIsAvatar(Integer isAvatar) {
        this.isAvatar = isAvatar;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}