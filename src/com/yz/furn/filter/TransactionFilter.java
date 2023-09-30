package com.yz.furn.filter;

import com.yz.furn.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JDBCUtilsByDruid.commit();
        } catch (Exception e) {
            JDBCUtilsByDruid.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
