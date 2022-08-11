package com.atguigu.autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: LeahAna
 * @Date: 2022/8/11 11:00
 * @Desc: 腾讯云点播配置
 */
@Data
@ConfigurationProperties(prefix = "ggkt.vod")
public class VodProperties {
    private String secretId;
    private String secretKey;
    private String region;
    private String procedure;
}
