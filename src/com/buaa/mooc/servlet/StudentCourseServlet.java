package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.dao.TeacherCourseDao;
import com.buaa.mooc.entity.Course;
import com.buaa.mooc.entity.Teacher;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by huxia on 2017/7/1.
 */
public class StudentCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("/login");
            return;
        }
        try {
            Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
            CourseDao courseDao = new CourseDao();
            Course course = courseDao.findByCid(cid);
            TeacherCourseDao teacherCourseDao = new TeacherCourseDao();
            List<Teacher> teachers = teacherCourseDao.findByCid(cid);
            request.setAttribute("course", course);
            request.setAttribute("teachers", teachers);

            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_course.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
