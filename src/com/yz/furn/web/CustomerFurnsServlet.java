package com.yz.furn.web;

import com.yz.furn.entity.Furn;
import com.yz.furn.entity.Page;
import com.yz.furn.service.impl.FurnServiceImpl;
import com.yz.furn.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings({"all"})
public class CustomerFurnsServlet extends BasicServlet {
    private FurnServiceImpl furnService = new FurnServiceImpl();

    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) name = "";
        String pagesize = request.getParameter("pagesize");
        String pageno = request.getParameter("pageno");
        StringBuilder url = new StringBuilder(request.getContextPath() + "/customer/customerFurns?action=search&");
        if (!name.equals("")) url.append("name=" + name);
        Page<Furn> pageByName = furnService.getPageByName(name, WebUtils.parseInt(pagesize, Page.PAGESIZE), WebUtils.parseInt(pageno, Page.PAGENO));
        pageByName.setUrl(url.toString());
        request.setAttribute("page", pageByName);
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request, response);
    }
}
