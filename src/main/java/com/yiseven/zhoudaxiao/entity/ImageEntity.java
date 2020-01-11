package com.yiseven.zhoudaxiao.entity;

public class ImageEntity {
    private Integer id;

    private Integer productId;

    private String url;

    private Boolean isCarousel;

    private Boolean isAvatar;

    private Integer priority;

    private String bucketKey;

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

    public Boolean getIsCarousel() {
        return isCarousel;
    }

    public void setIsCarousel(Boolean isCarousel) {
        this.isCarousel = isCarousel;
    }

    public Boolean getIsAvatar() {
        return isAvatar;
    }

    public void setIsAvatar(Boolean isAvatar) {
        this.isAvatar = isAvatar;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getBucketKey() {
        return bucketKey;
    }

    public void setBucketKey(String bucketKey) {
        this.bucketKey = bucketKey == null ? null : bucketKey.trim();
    }
}