package com.atguigu.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: LeahAna
 * @Date: 2022/7/14 16:46
 * @Desc:
 */
public interface FileUploadService {
    String uploadFile(MultipartFile multipartFile);
}
