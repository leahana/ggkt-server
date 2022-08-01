package com.atguigu.ggkt.service_vod_test;

import com.atguigu.autoconfig.template.OssTemplate;
import com.atguigu.ggkt.vod.ServiceVodApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author: LeahAna
 * @Date: 2022/7/14 16:20
 * @Desc: OssTemplate测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceVodApplication.class)
public class OssTemplateTest {

    @Autowired
    private OssTemplate ossTemplate;

    @Test
    public void uploadTest() throws FileNotFoundException {
        String path = "/Users/anshengyo/Downloads/IMG_3388.JPG";
        FileInputStream is = new FileInputStream(path);

        String imageUrl = ossTemplate.upload(path, is);
        System.out.println("上传成功：" + imageUrl);
    }

}
