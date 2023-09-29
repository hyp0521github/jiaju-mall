package com.yz.furn.dao.impl;

import com.yz.furn.dao.BasicDao;
import com.yz.furn.dao.OrderDao;
import com.yz.furn.entity.Order;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class OrderDaoImpl extends BasicDao<Order> implements OrderDao {
    @Override
    public List<Order> queryOrderList(int member_id) {
        String sql = "SELECT id, member_id, order_date, total_amount, `status` FROM `order` WHERE member_id = ?";
        return queryMulti(sql, Order.class, member_id);
    }

    @Override
    public int addOrder(Order order) {
        String sql = "INSERT INTO `order`(id, member_id, order_date, total_amount, status) VALUES (?, ?, ?, ?, ?)";
        return update(sql, order.getId(), order.getMemberId(), order.getOrderDate(), order.getTotalAmount(), order.getStatus());
    }
}
