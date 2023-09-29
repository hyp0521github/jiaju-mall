package com.yz.furn.service.impl;

import com.yz.furn.dao.OrderItemDao;
import com.yz.furn.dao.impl.OrderItemImpl;
import com.yz.furn.entity.OrderItem;
import com.yz.furn.service.OrderItemService;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao = new OrderItemImpl();
    @Override
    public List<OrderItem> getOrderItem(String orderId) {
        return orderItemDao.queryOrderItemList(orderId);
    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        return orderItemDao.addOrderItem(orderItem) == 1 ? true : false;
    }
}
