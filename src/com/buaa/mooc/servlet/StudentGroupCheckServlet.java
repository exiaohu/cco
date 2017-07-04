package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.entity.GroupRecruit;
import com.buaa.mooc.entity.StudentRecruitView;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by huxia on 2017/7/3.
 */
public class StudentGroupCheckServlet extends HttpServlet {
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

            GroupRecruitDao groupRecruitDao = new GroupRecruitDao();
            List<StudentRecruitView> studentRecruitViews = groupRecruitDao.findByGridSRV(grid);
            GroupRecruit groupRecruit = groupRecruitDao.findByGridGR(grid);

            request.setAttribute("studentRecruitViews", studentRecruitViews);
            request.setAttribute("groupRecruit", groupRecruit);

            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_group_check.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
