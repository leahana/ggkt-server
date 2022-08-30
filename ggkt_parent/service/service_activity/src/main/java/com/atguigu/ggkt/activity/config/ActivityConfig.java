package com.atguigu.ggkt.activity.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: LeahAna
 * @Date: 2022/8/29 14:41
 * @Desc: 营销模块配置类
 */

@Configuration
@MapperScan("com.atguigu.ggkt.activity.mapper")
public class ActivityConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
