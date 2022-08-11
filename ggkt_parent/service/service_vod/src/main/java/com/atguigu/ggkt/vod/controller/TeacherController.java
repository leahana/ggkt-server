package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.exceptionHandler.GgktException;
import com.atguigu.ggkt.interfacle.Result;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: LeahAna
 * @Date: 2022/7/8 10:56
 * @Desc: 讲师 前端控制器
 */
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping(value = "/admin/vod/teacher")
//@CrossOrigin // 允许跨域
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 逻辑删除讲师
     *
     * @param id 讲师id
     * @return 删除结果
     */
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeById(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    /**
     * 查询所有讲师列表
     *
     * @return 所有讲师信息
     */
    @ApiOperation("查询所有讲师列表")
    @GetMapping("findAll")
    public Result findAll() {
        List<Teacher> list = teacherService.list();
        return Result.ok(list).message(" ");
    }

    /**
     * 条件查询教师分页列表
     *
     * @param page           当前页码
     * @param limit          每页大小(pageSize)
     * @param teacherQueryVo 查询条件
     * @return 分页查询结果
     */
    @ApiOperation(value = "获取分页列表")
    @PostMapping("findQueryPage/{page}/{limit}")
    public Result pageTeachers(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherVo", value = "条件教师Vo对象", required = false)
            @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        //创建page对象，传递当前页和每页记录数
        Page<Teacher> pageParam = new Page<>(page, limit);
        //获取条件值
        String name = teacherQueryVo.getName();//讲师名称
        Integer level = teacherQueryVo.getLevel();//讲师级别
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();//开始时间
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();//结束时间
        //封装条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {
            wrapper.ge("join_date", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            wrapper.le("join_date", joinDateEnd);
        }
        //调用方法得到分页查询结果
        IPage<Teacher> pageModel = teacherService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }

    /**
     * 新增教师
     *
     * @param teacher 教师信息
     * @return 新增结果
     */
    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result saveTeacher(
            @ApiParam(name = "teacher", value = "教师对象", required = true)
            @RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return Result.ok(null);
    }


    /**
     * 修改教师信息
     *
     * @param teacher 教师信息
     * @return 修改结果
     */
    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(
            @ApiParam(name = "teacher", value = "教师对象", required = true)
            @RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return Result.ok(null);
    }

    /**
     * 查询教师详情
     *
     * @param id 教师id
     * @return 教师信息
     */
    @ApiOperation(value = "查询")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(
            @ApiParam(name = "id", value = "教师id", required = true)
            @PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    /**
     * 批量删除讲师
     * @param idList 讲师id集合
     * @return  删除结果
     */
    @ApiOperation(value = "批量删除讲师")
    @DeleteMapping("removeBatch")
    public Result removeBatch(
            @ApiParam(name = "idList",value = "讲师id集合",required = true)
            @RequestBody List<Long> idList) {

        boolean isSuccess = teacherService.removeByIds(idList);
        if (isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }
}
