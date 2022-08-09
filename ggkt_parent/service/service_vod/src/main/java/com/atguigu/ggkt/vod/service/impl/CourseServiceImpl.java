package com.atguigu.ggkt.vod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.model.vod.CourseDescription;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vod.mapper.CourseMapper;
import com.atguigu.ggkt.vod.service.CourseDescriptionService;
import com.atguigu.ggkt.vod.service.CourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author LeahAna
 * @since 2022-07-29
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


    @Autowired
    private CourseDescriptionService descriptionService;

    @Override
    public Map<String, Object> pageCourses(Page<Course> pageParam,
                                           CourseQueryVo courseQueryVo) {
        // step1: 获取条件
        String title = courseQueryVo.getTitle();
        Long subjectParentId = courseQueryVo.getSubjectParentId();// 一层分类
        Long subjectId = courseQueryVo.getSubjectId();// 二层分类
        Long teacherId = courseQueryVo.getTeacherId();
        // step2: 判断条件是否为空，封装条件
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(title), "title", title)
                .eq(ObjectUtil.isNotNull(subjectParentId), "subjectParentId", subjectParentId)
                .eq(ObjectUtil.isNotNull(subjectId), "subjectId", subjectId)
                .eq(ObjectUtil.isNotNull(teacherId), "teacherId", teacherId);
        // step3：条件查询分页
        Page<Course> coursePage = baseMapper.selectPage(pageParam, wrapper);
        List<Course> records = coursePage.getRecords();
        long totalCount = coursePage.getTotal();
        long totalPage = coursePage.getPages();
        // step4：封装数据

        Map<String, Object> map = new HashMap<>();

        map.put("records",records);
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);


        return map;
    }

    /**
     * 保存课程基本信息
     * @param courseFormVo 课程vo对象
     * @return Long courseId 课程id
     */
    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {

        // 保存课程基本信息
        Course course=new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.insert(course);

        // 保存课程详细信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setCourseId(course.getId());
        descriptionService.save(courseDescription);

        return course.getId();
    }
}
