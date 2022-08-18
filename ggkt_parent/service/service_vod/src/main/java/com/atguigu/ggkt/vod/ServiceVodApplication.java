package com.atguigu.ggkt.vod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: LeahAna
 * @Date: 2022/7/8 10:50
 * @Desc: service-vod 启动类
 */
@Slf4j
@ComponentScan("com.atguigu")
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class, args);
        log.info("service-vod start ・・ ・ー・・ ーーー ・・・ー ・ ー・ーー ーーー ・・ー");
     }
}


