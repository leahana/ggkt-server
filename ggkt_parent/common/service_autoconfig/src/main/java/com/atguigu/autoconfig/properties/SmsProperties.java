package com.atguigu.autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: leah_ana
 * @Date: 2022/4/9 0:11
 * @Desc: 阿里云短信服务配置
 */
@Data
@ConfigurationProperties(prefix = "ggkt.sms")
public class SmsProperties {
    private String signName;
    private String templateCode;
    private String accessId;
    private String accessSecret;
    private String  region;
}
