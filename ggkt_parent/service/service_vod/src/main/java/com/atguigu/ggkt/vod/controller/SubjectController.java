package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.interfacle.Result;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: LeahAna
 * @Date: 2022/7/19 14:10
 * @Desc: 课程管理分类
 */
@Api("课程管理分类")
@RestController
@RequestMapping(value = "/admin/vod/subject")
//@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 查询下一层课程分类
     *
     * @param id parent_id
     * @return 查询结果
     */
    @ApiOperation("查询下一层的课程分类")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(
            @ApiParam(value = "课程id")
            @PathVariable Long id) {
        List<Subject> list = subjectService.findChildSubject(id);
        return Result.ok(list);
    }

    /**
     * 导出课程分类
     *
     * @param response 包含导出数据Excel文件流的响应
     */
    @ApiOperation(value = "导出课程分类")
    @GetMapping(value = "exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }

    @ApiOperation("课程分类倒入")
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        subjectService.importData(file);
        return Result.ok(null);
    }
}

