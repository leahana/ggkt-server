package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.vod.mapper.TeacherMapper;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: LeahAna
 * @Date: 2022/7/8 11:01
 * @Desc: 讲师业务层
 */

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper,Teacher>
        implements TeacherService {

}
