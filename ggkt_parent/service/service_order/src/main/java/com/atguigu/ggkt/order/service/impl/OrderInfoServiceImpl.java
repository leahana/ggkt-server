package com.atguigu.ggkt.order.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.sql.Order;
import com.atguigu.ggkt.model.base.BaseEntity;
import com.atguigu.ggkt.model.order.OrderDetail;
import com.atguigu.ggkt.model.order.OrderInfo;
import com.atguigu.ggkt.order.mapper.OrderInfoMapper;
import com.atguigu.ggkt.order.service.OrderDetailService;
import com.atguigu.ggkt.order.service.OrderInfoService;
import com.atguigu.ggkt.vo.order.OrderInfoQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author LeahAna
 * @since 2022-08-15
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
    @Autowired
    private OrderDetailService orderDetailService;

    //订单列表
    @Override
    public Map<String, Object> selectOrderInfoPage(Page<OrderInfo> pageParam,
                                                   OrderInfoQueryVo orderInfoQueryVo) {
        //orderInfoQueryVo获取查询条件
        Long userId = orderInfoQueryVo.getUserId();
        String outTradeNo = orderInfoQueryVo.getOutTradeNo();
        String phone = orderInfoQueryVo.getPhone();
        String createTimeEnd = orderInfoQueryVo.getCreateTimeEnd();
        String createTimeBegin = orderInfoQueryVo.getCreateTimeBegin();
        Integer orderStatus = orderInfoQueryVo.getOrderStatus();

        //判断条件值是否为空，不为空，进行条件封装
        LambdaQueryWrapper<OrderInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ObjectUtil.isNotNull(orderStatus), OrderInfo::getOrderStatus, orderStatus)
                .eq(ObjectUtil.isNotEmpty(userId), OrderInfo::getUserId, userId)
                .eq(StrUtil.isNotEmpty(outTradeNo), OrderInfo::getOutTradeNo, orderStatus)
                .eq(StrUtil.isNotEmpty(phone), OrderInfo::getPhone, phone)
                .ge(StrUtil.isNotEmpty(createTimeBegin), OrderInfo::getCreateTime, createTimeBegin)
                .le(StrUtil.isNotEmpty(createTimeEnd), OrderInfo::getCreateTime, createTimeEnd);

        //调用实现条件分页查询
        Page<OrderInfo> pages = baseMapper.selectPage(pageParam, lqw);
        long totalCount = pages.getTotal();
        long pageCount = pages.getPages();
        List<OrderInfo> records = pages.getRecords();
        //订单里面包含详情内容，封装详情数据，根据订单id查询详情
        records = records.stream().map(value -> this.getOrderDetail(getOrderDetail(value))).collect(Collectors.toList());

        //所有需要数据封装map集合，最终返回
        Map<String, Object> map = new HashMap<>();
        map.put("total", totalCount);
        map.put("pageCount", pageCount);
        map.put("records", records);
        return map;
    }

    //查询订单详情数据
    private OrderInfo getOrderDetail(OrderInfo orderInfo) {
        //订单id
        Long id = orderInfo.getId();
        //查询订单详情
        OrderDetail orderDetail = orderDetailService.getById(id);
        if (orderDetail != null) {
            String courseName = orderDetail.getCourseName();
            orderInfo.getParam().put("courseName", courseName);
        }
        return orderInfo;
    }


}
