package com.yz.furn.dao;

import com.yz.furn.entity.OrderItem;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface OrderItemDao {
    // 查询订单详情
    List<OrderItem> queryOrderItemList(String order_id);

    // 添加订单详情
    int addOrderItem(OrderItem item);
}
