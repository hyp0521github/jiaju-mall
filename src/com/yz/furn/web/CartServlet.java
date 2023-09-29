package com.yz.furn.web;

import com.yz.furn.dao.FurnDao;
import com.yz.furn.dao.impl.FurnDaoImpl;
import com.yz.furn.entity.Cart;
import com.yz.furn.entity.CartItem;
import com.yz.furn.entity.Furn;
import com.yz.furn.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings({"all"})
public class CartServlet extends BasicServlet {
    private FurnDao furnDao = new FurnDaoImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Furn furn = furnDao.queryFurnById(WebUtils.parseInt(id, 0));
        if (furn == null) {
            // ?
            System.out.println("查询不到该商品");
            return;
        }
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        CartItem cartItem = new CartItem(furn.getId(), furn.getname(), furn.getPrice(), 1, furn.getPrice().multiply(new BigDecimal(1)));
        cart.add(cartItem, furn.getId());
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        Furn furn = furnDao.queryFurnById(id);
        if (furn == null) {
            // TODO: 2023/9/27  
            System.out.println("没有该商品信息");
            return;
        }
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null) {
            cart.update(id, count);
            System.out.println(cart.mp);
        }
        response.sendRedirect(request.getHeader("Referer"));

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Furn furn = furnDao.queryFurnById(id);
        if (furn == null) {
            // TODO: 2023/9/27
            System.out.println("没有该商品信息");
            return;
        }
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null) {
            cart.delete(id);
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null) {
            cart.clear();
        }
        response.sendRedirect(request.getHeader("Referer"));
    }
}
