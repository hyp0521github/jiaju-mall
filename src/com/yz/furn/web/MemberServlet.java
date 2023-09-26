package com.yz.furn.web;

import com.yz.furn.entity.Member;
import com.yz.furn.service.MemberService;
import com.yz.furn.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings({"all"})
public class MemberServlet extends BasicServlet {
    private MemberService memberService = new MemberServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (memberService.login(username, password) != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(60 * 60 * 24 * 3); // 设置session有效时间为三天
            request.getRequestDispatcher("/views/member/login_ok.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "输入的账号或密码有误");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
        }
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String email = (String) request.getParameter("email");
        String code = request.getParameter("code");
        // 获取服务器存储的session
        String token = (String)request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 验证验证码
        if(token != null && token.equalsIgnoreCase(code)) {
            Member member = new Member(null, username, password, email);
            // 查询用户是否存在
            if (!memberService.isExistMemberByUsername(username)) {
                boolean isSuccess = memberService.registerMember(member);
                if (isSuccess)
                    request.getRequestDispatcher("/views/member/register_ok.html").forward(request, response);
                else
                    request.getRequestDispatcher("/views/member/register_fail.html").forward(request, response);
            } else
                request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getContextPath());
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/");
    }
}
