package com.yiseven.zhoudaxiao.common.enums;

public enum QueryTypeEnum {
    BY_TODAY(1, "今日推荐"),
    BY_PAGE_VIEWS(2, "爆款推荐"),
    BY_CATEGORY(3, "通过类别查询");

    private Integer key;
    private String value;

    QueryTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
