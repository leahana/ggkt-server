package com.atguigu.ggkt.vod.mapper;

import com.atguigu.ggkt.model.vod.VideoVisitor;
import com.atguigu.ggkt.vo.vod.VideoVisitorCountVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Author: LeahAna
 * @Date: 2022/8/10 11:17
 * @Desc:
 */
public interface VideoVisitorMapper  extends BaseMapper<VideoVisitor> {
    //显示统计数据
    List<VideoVisitorCountVo> findCount(Long courseId, String startDate, String endDate);
}
