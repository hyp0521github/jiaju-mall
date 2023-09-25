package com.yz.furn.web;

import com.yz.furn.entity.Furn;
import com.yz.furn.entity.Page;
import com.yz.furn.service.impl.FurnServiceImpl;
import com.yz.furn.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings({"all"})
public class ManageFurnsServlet extends BasicServlet {
    private FurnServiceImpl furnService = new FurnServiceImpl();

    protected void queryFurns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Furn> furns = furnService.queryFurns();
        request.setAttribute("furns", furns);
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);
    }

    protected void addFurn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Furn furn = WebUtils.copyMapToBean(request.getParameterMap(), new Furn());
        boolean is_success = furnService.addFurn(furn);
        if (is_success) {
            // 原因：当用户提交完请求，浏览器会记录下最后一次请求的全部信息。当用户刷新页面(f5)，就会发起浏览器记录的上一次请求
            response.sendRedirect(String.format("/manage/manageFurns?action=page&pagesize=%d&pageno=%s", 5, request.getParameter("pageno")));
        } else {
            request.setAttribute("error", "添加商品失败");
            request.getRequestDispatcher("/views/manage/furn_add.jsp").forward(request, response);
        }
    }

    protected void delFurns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        furnService.delFurn(id);
        response.sendRedirect(String.format("/manage/manageFurns?action=page&pagesize=%d&pageno=%s", 5, request.getParameter("pageno")));
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Furn furn = furnService.get(WebUtils.parseInt(id, 0));
        // 请求转发到下一个页面，在下一个页面可以通过 param -pageno拿到上一个页面传过来的数据
        request.setAttribute("pageno", request.getParameter("pageno"));
        request.setAttribute("furn", furn);
        request.getRequestDispatcher("/views/manage/furn_update.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Furn furn = new Furn();
        WebUtils.copyMapToBean(request.getParameterMap(), furn);
        boolean is_success = furnService.update(furn);
        if (is_success) {
            response.sendRedirect(String.format("/manage/manageFurns?action=page&pagesize=%d&pageno=%s", 5, request.getParameter("pageno")));
        } else {
            request.setAttribute("error", "修改失败");
            request.getRequestDispatcher("/views/manage/furn_update.jsp").forward(request, response);
        }
    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pagesize = WebUtils.parseInt(request.getParameter("pagesize"), Page.PAGESIZE);
        int pageno = WebUtils.parseInt(request.getParameter("pageno"), 1);
        Page page = furnService.getPage(pagesize, pageno);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);
    }
}
