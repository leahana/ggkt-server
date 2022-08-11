package com.atguigu.ggkt.vod.service.impl;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.atguigu.autoconfig.template.VodTemplate;
import com.atguigu.ggkt.model.vod.Video;
import com.atguigu.ggkt.vod.mapper.VideoMapper;
import com.atguigu.ggkt.vod.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author LeahAna
 * @since 2022-07-29
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodTemplate vodTemplate;

    /**
     * 删除课程，删除所有课程相关视频
     * @param id 课程id
     */
    @Override
    public void removeVideoByCourseId(Long id) {
        LambdaQueryWrapper<Video> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Video::getCourseId, id);
        List<Video> videos = baseMapper.selectList(lqw);
        if (CollectionUtil.isNotEmpty(videos)) {
            for (Video video : videos) {
                String videoSourceId = video.getVideoSourceId();
                if (StrUtil.isNotEmpty(videoSourceId)){
                    vodTemplate.removeVideo(videoSourceId);
                }
            }
        }
        remove(lqw);
    }

    /**
     * 删除小节和小节视频
     * @param id 课程小节id
     */
    @Override
    public void removeVideoById(Long id) {
        LambdaQueryWrapper<Video> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Video::getChapterId, id);
        Video one = getOne(lqw);
        if (ObjectUtil.isNotNull(one)){
            String videoSourceId = one.getVideoSourceId();
            if (StrUtil.isNotEmpty(videoSourceId)){
                vodTemplate.removeVideo(videoSourceId);
            }
        }
        remove(lqw);
    }

}
