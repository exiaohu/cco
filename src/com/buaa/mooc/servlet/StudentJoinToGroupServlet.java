package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.StudentJoinGroupDao;
import com.buaa.mooc.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huxia on 2017/7/2.
 */
public class StudentJoinToGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("/login");
            return;
        }
        try {
            Integer grid = Integer.parseInt(request.getParameterMap().get("grid")[0]);
            Integer sid = (Integer) request.getSession().getAttribute("sid");
            Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);

            StudentJoinGroupDao studentJoinGroupDao = new StudentJoinGroupDao();
            studentJoinGroupDao.AddRelationSGR(sid, grid, 0);

            response.sendRedirect("StudentGroupHome?cid="+cid);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
