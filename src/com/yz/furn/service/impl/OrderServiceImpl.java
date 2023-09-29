package com.yz.furn.service.impl;

import com.yz.furn.dao.FurnDao;
import com.yz.furn.dao.OrderDao;
import com.yz.furn.dao.OrderItemDao;
import com.yz.furn.dao.impl.FurnDaoImpl;
import com.yz.furn.dao.impl.OrderDaoImpl;
import com.yz.furn.dao.impl.OrderItemImpl;
import com.yz.furn.entity.Cart;
import com.yz.furn.entity.CartItem;
import com.yz.furn.entity.Order;
import com.yz.furn.entity.OrderItem;
import com.yz.furn.service.OrderService;
import com.yz.furn.utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemImpl();
    private FurnDao furnDao = new FurnDaoImpl();

    @Override
    public List<Order> getOrder(int member_id) {
        return orderDao.queryOrderList(member_id);
    }

    @Override
    public Order addOrder(Cart cart, int member_id) {
        // 1.生成订单
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, member_id, new Date(), cart.getTotalPrice(), 0);
        int rows = orderDao.addOrder(order);
        // 2.生成订单详情同步修改furn的销量和库存
        if(rows == 1) {
            for (Map.Entry<Integer, CartItem> entry : cart.getMp().entrySet()) {
                CartItem cartItem = entry.getValue();
                // 生成订单详情
                OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getPrice(), cartItem.getCount(), cartItem.getTotalPrice(), orderId);
                orderItemDao.addOrderItem(orderItem);
                // 修改furn
                furnDao.updateFurnInSalesAndStock(cartItem.getCount(), cartItem.getId());
            }
        }
        // 3.清除购物车订单
        cart.clear();
        return order;
    }
}
