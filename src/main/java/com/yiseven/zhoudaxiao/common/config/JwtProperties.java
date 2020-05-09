package com.yiseven.zhoudaxiao.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jwt相关配置
 */
@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
@Data
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";

    private String secret;

    private Long expiration;

    private String md5Key;
}
