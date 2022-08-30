package com.atguigu.ggkt.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: LeahAna
 * @Date: 2022/8/29 15:12
 * @Desc: 用户服务启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.atguigu.ggkt.user.mapper")
public class ServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
