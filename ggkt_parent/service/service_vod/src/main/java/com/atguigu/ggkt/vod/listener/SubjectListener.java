package com.atguigu.ggkt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: LeahAna
 * @Date: 2022/7/29 15:55
 * @Desc: 读取操作的监听器
 */

@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {

    @Autowired
    private SubjectMapper subjectMapper;

    // 一行一行读取，从第二行开始
    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
        Subject subject =new Subject();
        // SubjectEeVo -- subject
        BeanUtils.copyProperties(subjectEeVo,subject);
        // 添加
        subjectMapper.insert(subject);
    }

    //
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
