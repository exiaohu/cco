package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.dao.TeacherCourseDao;
import com.buaa.mooc.dao.TeacherHomeworkCheckDao;
import com.buaa.mooc.entity.Course;
import com.buaa.mooc.entity.HomeworkSubmit;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2017/7/3.
 */
public class TeacherGradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        TeacherCourseDao teacherCourseDao = new TeacherCourseDao();
        Integer tid = (Integer) request.getSession().getAttribute("tid");
        List<Course> courses = teacherCourseDao.findByTid(tid);
        request.setAttribute("courses",courses);

        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_grade.jsp");
        rd.forward(request, response);
    }
}
