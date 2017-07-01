package com.buaa.mooc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huxia on 2017/7/1.
 */
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("sid", null);
        request.getSession().setAttribute("sname", null);
        request.getSession().setAttribute("tid", null);
        request.getSession().setAttribute("tname", null);
        request.getSession().setAttribute("eid", null);
        request.getSession().setAttribute("ename", null);
        response.sendRedirect("/login");
    }
}
