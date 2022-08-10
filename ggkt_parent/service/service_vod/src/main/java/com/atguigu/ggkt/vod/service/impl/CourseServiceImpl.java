package com.atguigu.ggkt.vod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.model.vod.CourseDescription;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CoursePublishVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vod.mapper.CourseMapper;
import com.atguigu.ggkt.vod.service.ChapterService;
import com.atguigu.ggkt.vod.service.CourseDescriptionService;
import com.atguigu.ggkt.vod.service.CourseService;
import com.atguigu.ggkt.vod.service.SubjectService;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.atguigu.ggkt.vod.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseDescriptionService descriptionService;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;



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
        wrapper.eq(ObjectUtil.isNotNull(subjectParentId), "subject_parent_id", subjectParentId)
                .eq(ObjectUtil.isNotNull(subjectId), "subject_id", subjectId)
                .eq(ObjectUtil.isNotNull(teacherId), "teacher_id", teacherId)
                .like(StringUtils.isNotEmpty(title), "title", title);

        // step3：条件查询分页
        Page<Course> coursePage = baseMapper.selectPage(pageParam, wrapper);
        List<Course> records = coursePage.getRecords();
        long totalCount = coursePage.getTotal();
        long totalPage = coursePage.getPages();
        // step4：封装数据
        //遍历封装讲师和分类名称
        records.forEach(this::getTeacherOrSubjectName);

        Map<String, Object> map = new HashMap<>();

        map.put("records", records);
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);


        return map;
    }

    /**
     * 保存课程基本信息
     *
     * @param courseFormVo 课程vo对象
     * @return Long courseId 课程id
     */
    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {

        // 保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        baseMapper.insert(course);

        // 保存课程详细信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setCourseId(course.getId());
        descriptionService.save(courseDescription);

        return course.getId();
    }

    /**
     * 根据id 查询课程基本信息和描述信息
     * @param id 课程id
     * @return vo结果
     */
    @Override
    public CourseFormVo getCourseInfo(Long id) {
        // 课程基本信息
        Course course = baseMapper.selectById(id);

        if(ObjectUtil.isNull(course)) {
            return null;
        }

        // 课程描述信息
        CourseDescription courseDescription = descriptionService.getByCourseId(id);

        // 封 装
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course,courseFormVo);
        if (ObjectUtil.isNotNull(courseDescription)){
            courseFormVo.setDescription(courseDescription.getDescription());
        }
        return courseFormVo;
    }

    /**
     * 更新课程基本信息和描述信息
     * @param courseFormVo 封装的课程信息
     */
    @Override
    public void updateCourseId(CourseFormVo courseFormVo) {
        // 修改课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);

        baseMapper.updateById(course);

        // 修改课程描述信息
        CourseDescription courseDescription = descriptionService.getByCourseId(course.getId());
        if (ObjectUtil.isNotNull(courseDescription)){
            courseDescription.setDescription(courseFormVo.getDescription());
            courseDescription.setId(course.getId());
            descriptionService.updateById(courseDescription);
        }else {
            // 保存课程详细信息
            courseDescription = new CourseDescription();
            courseDescription.setDescription(courseFormVo.getDescription());
            courseDescription.setCourseId(course.getId());
            descriptionService.save(courseDescription);
        }

    }
    //根据id获取课程发布信息
    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return courseMapper.selectCoursePublishVoById(id);
    }

    //根据id发布课程
    @Override
    public boolean publishCourseById(Long id) {
        Course course = new Course();
        course.setId(id);
        course.setPublishTime(new Date());
        course.setStatus(1);
        return this.updateById(course);
    }

    // 获取讲师和分类名称
    private void getTeacherOrSubjectName(Course course) {
        //查询讲师名称
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if (ObjectUtil.isNotNull(teacher)) {
            course.getParam().put("teacherName", teacher.getName());
        }
        // 查询分类名称
        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if (ObjectUtil.isNotNull(subjectOne)) {
            course.getParam().put("subjectParentTitle", subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if (ObjectUtil.isNotNull(subjectTwo)) {
            course.getParam().put("subjectTitle", subjectTwo.getTitle());
        }
    }


    //删除课程
    @Override
    public void removeCourseById(Long id) {
        //根据课程id删除小节
        videoService.removeVideoByCourseId(id);
        //根据课程id删除章节
        chapterService.removeChapterByCourseId(id);
        //根据课程id删除描述
        descriptionService.removeByCourseId(id);
        //根据课程id删除课程
        baseMapper.deleteById(id);
    }
}
