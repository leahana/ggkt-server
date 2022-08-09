package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.interfacle.Result;
import com.atguigu.ggkt.vod.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: LeahAna
 * @Date: 2022/7/14 16:43
 * @Desc: 文件上传
 */

@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/admin/vod/file")
@CrossOrigin
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public Result uploadFile(
            @ApiParam(value = "文件")
            MultipartFile file) {
        if (file.isEmpty()) {
            return Result.fail(null).message("文件上传失败");
        }
        String url = fileUploadService.uploadFile(file);
        return Result.ok(url).message("文件上传成功");
    }

}
