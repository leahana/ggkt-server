package com.atguigu.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author LeahAna
 * @since 2022-07-29
 */
public interface CourseDescriptionService extends IService<CourseDescription> {

    CourseDescription getByCourseId(Long id);
}
