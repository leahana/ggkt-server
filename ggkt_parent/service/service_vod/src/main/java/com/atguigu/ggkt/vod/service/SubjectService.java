package com.atguigu.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: LeahAna
 * @Date: 2022/7/19 14:13
 * @Desc:
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 查询课程分类
     * @param id 课程id
     * @return 和parent_id关联的课程
     */
    List<Subject> findChildSubject(Long id);

    /**
     * 课程分类导出
     * @param response 包含Excel文件流的响应
     */
    void exportData(HttpServletResponse response);

    /**
     * 课程分类导入
     * @param file 导入的Excel文件流
     */
    void importData(MultipartFile file);
}
