package com.yz.furn.service;

import com.yz.furn.entity.OrderItem;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface OrderItemService {
    // 获取订单详情
    List<OrderItem> getOrderItem(String orderId);

    // 添加订单详情
    boolean addOrderItem(OrderItem orderItem);
}
