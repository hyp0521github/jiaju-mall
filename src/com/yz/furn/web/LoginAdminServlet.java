package com.yz.furn.web;

import com.yz.furn.entity.Admin;
import com.yz.furn.service.AdminService;
import com.yz.furn.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings({"all"})
public class LoginAdminServlet extends BasicServlet {
    private AdminService adminService = new AdminServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(adminService.isExistsAdmin(username)) {
            Admin admin = adminService.login(username, password);
            if(admin != null) {
                request.getRequestDispatcher("/views/manage/manage_menu.jsp").forward(request,response);
            } else {
                request.setAttribute("error", "用户名或密码错误");
                request.setAttribute("username", username);
                request.getRequestDispatcher("/views/manage/manage_login.jsp").forward(request,response);
            }
        } else {
            request.setAttribute("error", "没有该用户");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/views/manage/manage_login.jsp").forward(request,response);
        }
    }
}
