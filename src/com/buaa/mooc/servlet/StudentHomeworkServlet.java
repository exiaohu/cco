package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.dao.StudentHomeworkDao;
import com.buaa.mooc.entity.Homework;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by windrises on 2017/6/28.
 */
public class StudentHomeworkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("login");
            return;
        }
        try {
            Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
            List<Homework> homeworks = new StudentHomeworkDao().getHomeworkByCid(cid);
            request.setAttribute("homeworks", homeworks);
            request.setAttribute("course", new CourseDao().findByCid(cid));
            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_homework.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.sendRedirect("student");
        }
    }
}
