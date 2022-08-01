package com.atguigu.ggkt.vod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: LeahAna
 * @Date: 2022/7/8 10:50
 * @Desc: service_vod 启动类
 */
@Slf4j
@ComponentScan("com.atguigu")
@SpringBootApplication
public class ServiceVodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class, args);
        log.info("service_vod start ! ! !");
     }
}


