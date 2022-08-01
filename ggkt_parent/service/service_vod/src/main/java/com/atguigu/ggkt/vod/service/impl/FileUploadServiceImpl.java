package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.autoconfig.template.OssTemplate;
import com.atguigu.ggkt.vod.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: LeahAna
 * @Date: 2022/7/14 16:47
 * @Desc:
 */

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private OssTemplate ossTemplate;

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        String url = "";
        try {
            url = ossTemplate.upload(multipartFile.getOriginalFilename(), multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
