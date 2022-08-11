package com.atguigu.ggkt.service_vod_test;

import com.atguigu.autoconfig.properties.VodProperties;
import com.atguigu.autoconfig.template.VodTemplate;
import com.atguigu.ggkt.utils.Signature;
import com.atguigu.ggkt.vod.ServiceVodApplication;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @Author: LeahAna
 * @Date: 2022/8/11 10:30
 * @Desc: 云点播组件测试模块
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceVodApplication.class)
@Slf4j
public class VodTemplateTest {


    @Test
    public void testUploadTest() {
        // 初始化一个上传客户端对象
        VodUploadClient client = new VodUploadClient(
                "", "");

        // 构造上传请求对象
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath("/Users/anshengyo/Downloads/连锁攻击！！.MOV");
        request.setCoverFilePath("/Users/anshengyo/Downloads/IMG_0570.JPG");

        try {
            VodUploadResponse response = client.upload("ap-chongqing", request);
            log.info("Upload FileId = {}", response.getFileId());
        } catch (Exception e) {
            // 业务方进行异常处理
            log.error("Upload Err", e);
        }
    }

    @Autowired
    private VodTemplate vodTemplate;

    @Test
    public void testUploadVideo() {
        vodTemplate.uploadVideo(null, null);
    }

    @Autowired
    private VodProperties properties;

    @Test
    public void testGetSignature() {
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(properties.getSecretId());
        sign.setSecretKey(properties.getSecretKey());
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            log.info("获取签名成功：signature ={}" + signature);
        } catch (Exception e) {
            log.info("获取签名失败");
            e.printStackTrace();
        }
    }
}
