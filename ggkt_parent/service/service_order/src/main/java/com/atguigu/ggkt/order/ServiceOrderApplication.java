package com.atguigu.ggkt.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: LeahAna
 * @Date: 2022/8/15 14:25
 * @Desc: 订单服务启动类
 */

@Slf4j
@SpringBootApplication
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
        log.info("ServiceOrder start・・ ・ー・・ ーーー ・・・ー ・ ー・ーー ーーー ・・ー");
    }
}
