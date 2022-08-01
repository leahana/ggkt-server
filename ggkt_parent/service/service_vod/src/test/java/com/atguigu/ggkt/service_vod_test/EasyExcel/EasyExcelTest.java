package com.atguigu.ggkt.service_vod_test.EasyExcel;

import com.alibaba.excel.EasyExcel;
import com.atguigu.ggkt.vod.ServiceVodApplication;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LeahAna
 * @Date: 2022/7/29 14:17
 * @Desc: EasyExcel测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceVodApplication.class)
public class EasyExcelTest {
    private  static final String fileName = "/Users/anshengyo/Downloads/11.xlsx";

    @Test
    public void testWrite()throws Exception {
        // 写法1
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, Stu.class).sheet("写入方法").doWrite(data());
    }
    @Test
    public void testRead() throws Exception{
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Stu.class, new ExcelListener()).sheet().doRead();
    }

        //循环设置要添加的数据，最终封装到list集合中
        private static List<Stu> data () {
            List<Stu> list = new ArrayList<Stu>();
            for (int i = 0; i < 10; i++) {
                Stu data = new Stu();
                data.setSno(i);
                data.setSname("张三" + i);
                list.add(data);
            }
            return list;
        }
    }


