package com.yz.furn.web;

import com.yz.furn.entity.Member;
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
public class RegisterMemberServlet extends BasicServlet {
    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String email = (String) request.getParameter("email");
        Member member = new Member(null, username, password, email);
        if (!memberService.isExistMemberByUsername(username)) {
            boolean isSuccess = memberService.registerMember(member);
            if (isSuccess)
                request.getRequestDispatcher("/views/member/register_ok.html").forward(request, response);
            else
                request.getRequestDispatcher("/views/member/register_fail.html").forward(request, response);
        } else
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
    }
}
