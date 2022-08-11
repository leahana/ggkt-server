package com.atguigu.ggkt.vod.service;

import java.io.InputStream;

/**
 * @Author: LeahAna
 * @Date: 2022/8/11 11:25
 * @Desc: 云点播接口
 */
public interface VodService {

    String uploadVideo(InputStream inputStream, String originalFilename);

    void removeVideo(String videoSourceId);

    String getSign();
}
