package com.yz.furn.test;

import com.yz.furn.entity.Order;
import com.yz.furn.service.OrderService;
import com.yz.furn.service.impl.OrderServiceImpl;
import com.yz.furn.utils.WebUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class OrderTest {
    private OrderService orderService = new OrderServiceImpl();

    public static void main(String[] args) {
        //System.out.println(UUID.randomUUID().toString());
    }

    @Test
    public void testOrder() {

    }

    @Test
    public void testDate() {
        System.out.println(new Date());
    }
}

