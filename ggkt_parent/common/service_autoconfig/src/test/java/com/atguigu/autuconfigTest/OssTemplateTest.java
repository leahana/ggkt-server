package com.atguigu.autuconfigTest;

import com.atguigu.AutoConfigTestApplication;
import com.atguigu.autoconfig.template.OssTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author: LeahAna
 * @Date: 2022/7/14 16:20
 * @Desc: OssTemplate测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoConfigTestApplication.class)
public class OssTemplateTest {

    @Autowired
    private OssTemplate ossTemplate;

    @Test
    public void uploadTest() throws FileNotFoundException {
        String path = "/Users/anshengyo/Downloads/IMG_0570.JPG";
        FileInputStream is = new FileInputStream(path);

        String imageUrl = ossTemplate.upload(path, is);
        System.out.println("上传成功：" + imageUrl);
    }

}
