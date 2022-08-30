package com.atguigu.ggkt.activity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: LeahAna
 * @Date: 2022/8/29 14:37
 * @Desc: 营销模块
 */

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class ServiceActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceActivityApplication.class, args);
        log.info("service-activity start ・・ ・ー・・ ーーー ・・・ー ・ ー・ーー ーーー ・・ー");

    }
}
