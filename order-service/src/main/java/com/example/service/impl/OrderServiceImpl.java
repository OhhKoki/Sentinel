package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.client.UserClient;
import com.example.dto.UserDTO;
import com.example.entity.Order;
import com.example.mapper.OrderMapper;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    @Override
    public Order queryById(Long orderId) {
        // 1. 查询订单
        Order order = orderMapper.findById(orderId);
        // 2. 调用 user-service 服务查询用户信息
        UserDTO userDTO = userClient.selectById(order.getUserId());
        // 3. 将 user 设置到 order 中
        order.setUserDTO(userDTO);
        // 4. 返回
        return order;
    }

    /**
     * 默认情况下，OrderService 中的方法是不被 Sentinel 监控的，需要我们自己通过注解来标记要监控的方法。
     * 给 OrderService 的 queryGoods 方法添加 @SentinelResource 注解：
     */
    @SentinelResource(value = "goods")
    @Override
    public void queryGoods() {
        System.out.println("查询商品");
    }

}
