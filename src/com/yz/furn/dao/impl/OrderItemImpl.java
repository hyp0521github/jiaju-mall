package com.yz.furn.dao.impl;

import com.yz.furn.dao.BasicDao;
import com.yz.furn.dao.OrderItemDao;
import com.yz.furn.entity.OrderItem;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class OrderItemImpl extends BasicDao<OrderItem> implements OrderItemDao {
    @Override
    public List<OrderItem> queryOrderItemList(String order_id) {
        String sql = "SELECT id, `name`, price, `count`, total_price totalPrice, order_id FROM order_time WHERE order_id = ?";
        return queryMulti(sql, OrderItem.class, order_id);
    }

    @Override
    public int addOrderItem(OrderItem item) {
        String sql = "INSERT INTO `order_time`(id, `name`, price, `count`, total_price, order_id) VALUES (null, ?, ?, ?, ?, ?)";
        return update(sql, item.getName(), item.getPrice(), item.getCount(), item.getTotalPrice(), item.getOrderId());
    }
}
