package com.atguigu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: LeahAna
 * @Date: 2022/8/15 09:47
 * @Desc: 第三方服务自动注入测试类
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.atguigu.autoconfig"},
exclude = DataSourceAutoConfiguration.class)
public class AutoConfigTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoConfigTestApplication.class, args);
        log.info("AutoConfigTestApplication start・・ ・ー・・ ーーー ・・・ー ・ ー・ーー ーーー ・・ー");
    }
}


