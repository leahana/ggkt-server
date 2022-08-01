package com.atguigu.autoconfig;

import com.atguigu.autoconfig.properties.OssProperties;
import com.atguigu.autoconfig.template.OssTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Author: LeahAna
 * @Date: 2022/7/14 11:04
 * @Desc: 阿里云oss服务配置类
 */
@EnableConfigurationProperties(value = {OssProperties.class})
public class OssConfiguration {
        @Bean
        public OssTemplate ossTemplate(OssProperties properties) {
                return new OssTemplate(properties);
        }
}
