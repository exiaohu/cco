package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.TeacherCourseDao;
import com.buaa.mooc.entity.Course;
import com.buaa.mooc.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 我不承认 on 2017/7/4.
 */
public class TeacherGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        Integer tid = (Integer) request.getSession().getAttribute("tid");

        List<Course> courses = new TeacherCourseDao().findByTid(tid);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher( "/teacherGroup.jsp").forward(request,response);
    }
}
