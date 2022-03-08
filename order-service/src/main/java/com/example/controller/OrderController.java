package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.entity.Order;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @SentinelResource(value = "hot")
    @GetMapping("/{orderId}")
    public Order queryByUserId(@PathVariable("orderId") Long orderId) {
        log.info("订单 id 为：" + orderId);
        // 根据id查询订单并返回
        return orderService.queryById(orderId);
    }

    @GetMapping("/query")
    public String queryOrder() {
        return "查询订单成功";
    }

    @PutMapping("/update")
    public String updateOrder() {
        return "更新订单成功";
    }

    @DeleteMapping("/delete")
    public String deleteOrder() {
        // 查询商品
        orderService.queryGoods();
        return "删除订单成功";
    }

    @PostMapping("/insert")
    public String insertOrder() {
        // 查询商品
        orderService.queryGoods();
        return "新增订单成功";
    }

}
