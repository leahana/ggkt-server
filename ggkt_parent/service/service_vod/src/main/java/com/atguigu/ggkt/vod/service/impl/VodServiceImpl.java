package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.autoconfig.template.VodTemplate;
import com.atguigu.ggkt.utils.Signature;
import com.atguigu.ggkt.vod.service.VodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Random;

/**
 * @Author: LeahAna
 * @Date: 2022/8/11 11:25
 * @Desc: 云点播接口实现类
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {

    @Autowired
    private VodTemplate vodTemplate;

    @Override
    public String uploadVideo(InputStream inputStream, String originalFilename) {
        return vodTemplate.uploadVideo(inputStream, originalFilename);
    }

    @Override
    public void removeVideo(String videoSourceId) {
        vodTemplate.removeVideo(videoSourceId);
    }

    @Override
    public String getSign() {

        return vodTemplate.getSign();

    }
}
