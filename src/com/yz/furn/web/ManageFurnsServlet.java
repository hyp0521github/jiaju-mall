package com.yz.furn.web;

import com.yz.furn.entity.Furn;
import com.yz.furn.entity.Page;
import com.yz.furn.service.impl.FurnServiceImpl;
import com.yz.furn.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Furn furn = furnService.get(id);
        // 1.判断请求头文件类型
        if (ServletFileUpload.isMultipartContent(request)) {
            // 2.判断各个表单字段类型
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            // 解析表单字段类型对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            try {
                List<FileItem> list = servletFileUpload.parseRequest(request);
                for (FileItem fileItem : list) {
                    // 如果是二进制文件
                    if (!fileItem.isFormField()) {
                        // 3.保存文件
                        String saveDirectoryPath = "/" + WebUtils.FURN_IMG_PATH;
                        String fileRealDirectoryPath = getServletContext().getRealPath(saveDirectoryPath) + "/";
                        File fileRealDirectory = new File(fileRealDirectoryPath);
                        if (!fileRealDirectory.exists()) {
                            fileRealDirectory.mkdirs();
                        }
                        if (!fileItem.getName().equals("")) {
                            String fileName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + fileItem.getName();
                            try {
                                fileItem.write(new File(fileRealDirectoryPath + fileName));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            furn.setImgPath(WebUtils.FURN_IMG_PATH + "/" + fileItem.getName());
                        }
                    } else {
                        // 如果是表单字段
                        if (fileItem.getFieldName().equals("name")) {
                            furn.setname(fileItem.getString("utf-8"));
                        } else if (fileItem.getFieldName().equals("maker")) {
                            furn.setMaker(fileItem.getString("utf-8"));
                        } else if (fileItem.getFieldName().equals("price")) {
                            furn.setPrice(new BigDecimal(fileItem.getString()));
                        } else if (fileItem.getFieldName().equals("sales")) {
                            furn.setSales(new Integer(fileItem.getString()));
                        } else if (fileItem.getFieldName().equals("stock")) {
                            furn.setStock(new Integer(fileItem.getString()));
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("请求头类型错误");
        }
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
