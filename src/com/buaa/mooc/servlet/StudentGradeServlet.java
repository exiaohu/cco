package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by windrises on 2017/7/3.
 */
public class StudentGradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("/login");
            return;
        }
        Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
        request.setAttribute("course", new CourseDao().findByCid(cid));
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_grade.jsp");
        rd.forward(request, response);
    }
}
