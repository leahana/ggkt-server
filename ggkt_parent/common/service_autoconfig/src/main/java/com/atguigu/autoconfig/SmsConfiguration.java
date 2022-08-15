package com.atguigu.autoconfig;

import com.atguigu.autoconfig.properties.SmsProperties;

import com.atguigu.autoconfig.template.SmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Author: LeahAna
 * @Date: 2022/8/15 09:14
 * @Desc: 阿里云短信发送配置类
 */

@EnableConfigurationProperties(value = {SmsProperties.class})
public class SmsConfiguration {

    @Bean
    public SmsTemplate smsTemplate(SmsProperties properties){
        return new SmsTemplate(properties);
    }
}
