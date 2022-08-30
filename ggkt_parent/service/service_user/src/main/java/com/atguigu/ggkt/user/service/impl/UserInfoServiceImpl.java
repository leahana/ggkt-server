package com.atguigu.ggkt.user.service.impl;

import com.atguigu.ggkt.user.entity.UserInfo;
import com.atguigu.ggkt.user.mapper.UserInfoMapper;
import com.atguigu.ggkt.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author LeahAna
 * @since 2022-08-29
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
