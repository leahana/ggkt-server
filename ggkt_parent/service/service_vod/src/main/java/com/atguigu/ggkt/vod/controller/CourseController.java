package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.interfacle.Result;
import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vo.vod.CourseVo;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.ggkt.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author LeahAna
 * @since 2022-07-29
 */
@Api(tags = "课程管理接口")
@RestController
@RequestMapping("/admin/vod/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;


    @ApiOperation("点播课程列表")
    @GetMapping("{page}/{limit}")
    public Result pageCourses(@ApiParam(name = "page", value = "当前页码", required = true)
                              @PathVariable Long page,
                              @ApiParam(name = "limit", value = "每页记录数", required = true)
                              @PathVariable Long limit,
                              @ApiParam(name = "courseVo", value = "条件课程Vo对象", required = false)
                              @RequestBody(required = false) CourseQueryVo courseQueryVo) {

        Page<Course> pageParam = new Page<>(page, limit);

        Map<String, Object> map = courseService.pageCourses(pageParam, courseQueryVo);

        return Result.ok(map);
    }

    @ApiOperation("新增")
    @PostMapping("save")
    public Result save(
            @ApiParam(name = "courseVo", value = "新增课程Vo对象", required = false)
            @RequestBody(required = false) CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }
}

