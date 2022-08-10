package com.atguigu.ggkt.vod.service;

import java.util.Map;

/**
 * @Author: LeahAna
 * @Date: 2022/8/10 11:14
 * @Desc: VideoVisitor接口
 */

public interface VideoVisitorService {

    Map<String, Object> findCount(Long courseId, String startDate, String endDate);
}
