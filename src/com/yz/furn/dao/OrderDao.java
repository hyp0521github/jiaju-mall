package com.yz.furn.dao;

import com.yz.furn.entity.Order;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface OrderDao {
    // 查询订单列表
    List<Order> queryOrderList(int member_id);

    // 添加订单
    int addOrder(Order order);
}
