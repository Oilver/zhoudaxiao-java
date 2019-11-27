package com.yiseven.zhoudaxiao.web.request;

import lombok.Data;

import java.util.List;

@Data
public class ImageRequest {

    private Integer productId;

    private String avatarKey;

    private List<String> keys;

}
