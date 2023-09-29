package com.yz.furn.web;

import com.yz.furn.dao.MemberDao;
import com.yz.furn.dao.impl.MemberDaoImpl;
import com.yz.furn.entity.*;
import com.yz.furn.service.OrderItemService;
import com.yz.furn.service.OrderService;
import com.yz.furn.service.impl.OrderItemServiceImpl;
import com.yz.furn.service.impl.OrderServiceImpl;
import com.yz.furn.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings({"all"})
public class OrderServlet extends BasicServlet {
    private OrderService orderService = new OrderServiceImpl();
    private OrderItemService orderItemService = new OrderItemServiceImpl();
    private MemberDao memberDao = new MemberDaoImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("/login.jsp");
            return;
        }
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("/");
            return;
        }
        // 获取订单用户信息
        Member member = memberDao.queryMemberByUsername(username);
        Order order = orderService.addOrder(cart, member.getId());
        request.setAttribute("order", order);
        request.getRequestDispatcher("/views/order/order.jsp").forward(request, response);
    }

    protected void details(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItemList = orderItemService.getOrderItem(orderId);
        request.setAttribute("orderItemList", orderItemList);
        int totalCount = 0;
        BigDecimal totalPrice = new BigDecimal("0");
        for (OrderItem orderItem : orderItemList) {
            totalCount += orderItem.getCount();
            totalPrice = totalPrice.add(orderItem.getPrice());
        }
        request.setAttribute("totalCount", String.valueOf(totalCount));
        request.setAttribute("totalPrice", String.valueOf(totalPrice));
        request.getRequestDispatcher("/views/order/order_detail.jsp").forward(request, response);
    }
}
