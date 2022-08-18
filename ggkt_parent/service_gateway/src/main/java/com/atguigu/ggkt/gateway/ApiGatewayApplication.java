package com.atguigu.ggkt.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: LeahAna
 * @Date: 2022/8/11 19:14
 * @Desc: 网关启动类
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
        log.info("gateway start ・・ ・ー・・ ーーー ・・・ー ・ ー・ーー ーーー ・・ー");
    }
}
