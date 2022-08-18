package com.atguigu.ggkt.order.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: LeahAna
 * @Date: 2022/8/15 14:33
 * @Desc: service-order 配置类
 */
@Configuration
@MapperScan("com.atguigu.ggkt.order.mapper")
public class OrderConfig {

    /**
     * Mybatis-plus 分页查询插件
     * @return 分页查询拦截器bean
     */

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
