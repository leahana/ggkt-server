package com.atguigu.autoconfig;

import com.atguigu.autoconfig.properties.OssProperties;
import com.atguigu.autoconfig.properties.VodProperties;
import com.atguigu.autoconfig.template.VodTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Author: LeahAna
 * @Date: 2022/8/11 11:04
 * @Desc: 腾讯云点播服务类
 */
@EnableConfigurationProperties(value = {VodProperties.class})
public class VodConfiguration {

    @Bean
    public VodTemplate vodTemplate (VodProperties vodProperties){
        return new VodTemplate(vodProperties);
    }
}
