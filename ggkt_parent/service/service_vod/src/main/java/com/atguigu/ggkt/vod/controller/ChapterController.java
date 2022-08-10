package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.interfacle.Result;
import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.atguigu.ggkt.vod.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author LeahAna
 * @since 2022-07-29
 */
@Api("课程")
@RestController
@RequestMapping("/admin/vod/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    /**
     * 获取章节小结列表
     * @param courseId 课程id
     * @return 数据列表
     */
    @ApiOperation("嵌套章节数据列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getNestedTreeList(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long courseId){

        List<ChapterVo> chapterVoList = chapterService.getNestedTreeList(courseId);
        return Result.ok(chapterVoList);
    }

    /**
     * 添加章节
     * @param chapter 课程
     * @return 操作结果
     */
    @ApiOperation("添加章节")
    @PostMapping("save")
    public Result save(
            @ApiParam(value = "章节信息", required = true)
            @RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return Result.ok(null);
    }

    /**
     * 修改-根据id查询
     * @param id 课程id
     * @return 查询到的课程章节
     */
    @ApiOperation("修改-回显数据")
    @GetMapping("get/{id}")
    public Result get(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id) {
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }

    /**
     * 更新数据
     * @param chapter 需要修改的章节信息
     * @return 操作结果
     */
    @ApiOperation("修改-更新数据")
    @PostMapping("update")
    public Result update(
            @ApiParam(value = "修改的章节信息", required = true)
            @RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return Result.ok(null);
    }

    /**
     * 删除
     * @param id 课程id
     * @return 操作结果
     */
    @ApiOperation("根据id删除")
    @DeleteMapping("remove/{id}")
    public Result remove(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id) {
        chapterService.removeById(id);
        return Result.ok(null);
    }

}

