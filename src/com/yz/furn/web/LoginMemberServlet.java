package com.yz.furn.web;

import com.yz.furn.service.MemberService;
import com.yz.furn.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings({"all"})
public class LoginMemberServlet extends BasicServlet {
    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (memberService.login(username, password) != null) {
            request.getRequestDispatcher("/views/member/login_ok.html").forward(request, response);
        } else {
            request.setAttribute("msg", "输入的账号或密码有误");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
        }
    }
}
