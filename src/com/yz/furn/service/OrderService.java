package com.yz.furn.service;

import com.yz.furn.entity.Cart;
import com.yz.furn.entity.Order;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public interface OrderService {
    // 获取订单
    List<Order> getOrder(int member_id);

    // 添加订单
    Order addOrder(Cart cart, int member_id);
}
