package com.atguigu.autuconfigTest;

import com.atguigu.AutoConfigTestApplication;
import com.atguigu.autoconfig.template.SmsTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: LeahAna
 * @Date: 2022/8/15 10:20
 * @Desc:  SmsTemplate测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoConfigTestApplication.class)
public class SmsTemplateTest {

    @Autowired
    private SmsTemplate template;

    @Test
    public void testSendMsm() {
        template.sendSms("17714538023");
    };
}
