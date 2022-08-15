package com.atguigu.autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云审核配置
 */
@Data
//@ConfigurationProperties(prefix = "ggkt.green")
public class GreenProperties {
    /**
     * 账号
     */
    String accessKeyID;
    /**
     * 密钥
     */
    String accessKeySecret;

    /**
     * 场景
     */
    String imageScanScenes;

    /**
     * 文字
     */
    String txtScanScenes;


    String regionId;


    String endpoint;

}
