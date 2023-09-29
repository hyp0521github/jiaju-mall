package com.yz.furn.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class AutoFilter implements Filter {
    private List<String> excludeFilterUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludeFilterUrl = filterConfig.getInitParameter("excludeFilterUrl");
        String[] urls = excludeFilterUrl.split(",");
        excludeFilterUrls = Arrays.asList(urls);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hsq = (HttpServletRequest) servletRequest;
        String servletPath = hsq.getServletPath();
        String username = (String) hsq.getSession().getAttribute("username");
        if (!excludeFilterUrls.contains(servletPath)) {
            if (username == null) {
                hsq.getRequestDispatcher("/views/member/login.jsp").forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
