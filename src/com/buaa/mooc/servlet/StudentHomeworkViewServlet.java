package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.dao.HomeworkDao;
import com.buaa.mooc.entity.Homework;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huxia on 2017/7/1.
 */
public class StudentHomeworkViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("login");
            return;
        }
        try {
            Integer hid = Integer.parseInt(request.getParameterMap().get("hid")[0]);
            HomeworkDao homeworkDao = new HomeworkDao();
            Homework homework = homeworkDao.findByHid(hid);
            request.setAttribute("homework", homework);
            request.setAttribute("course", new CourseDao().findByCid(homework.getCourseId()));
            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_hwview.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
