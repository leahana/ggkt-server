package com.atguigu.autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: leah_ana
 * @Date: 2022/4/9 17:11
 * @Desc: 阿里云oss储存配置
 */
@Data
@ConfigurationProperties(prefix = "ggkt.oss")
public class OssProperties {
    private String accessId;
    private String accessSecret;
    private String bucketName;
    private String url; //域名
    private String endpoint;
}