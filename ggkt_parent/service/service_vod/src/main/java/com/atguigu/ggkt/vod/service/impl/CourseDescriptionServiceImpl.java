package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.CourseDescription;
import com.atguigu.ggkt.vod.mapper.CourseDescriptionMapper;
import com.atguigu.ggkt.vod.service.CourseDescriptionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author LeahAna
 * @since 2022-07-29
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {


    @Override
    public CourseDescription getByCourseId(Long id) {
        QueryWrapper<CourseDescription> qw = new QueryWrapper<>();
        qw.eq("course_id",id);
        return getOne(qw);
    }

    @Override
    public void removeByCourseId(Long id) {
        LambdaQueryWrapper<CourseDescription> lqw = new LambdaQueryWrapper<>();

        lqw.eq(CourseDescription::getCourseId, id);

        remove(lqw);

    }
}
