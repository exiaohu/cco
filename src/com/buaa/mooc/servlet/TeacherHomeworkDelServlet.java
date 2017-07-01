package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.HomeworkDao;
import com.buaa.mooc.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.IllegalFormatException;

/**
 * Created by lenovo on 2017/7/1.
 */
public class TeacherHomeworkDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer hid = Integer.parseInt(request.getParameter("hid"));
            HomeworkDao homeworkDao = new HomeworkDao();
            homeworkDao.delHomework(hid);
            response.sendRedirect("TeacherHomework");
        } catch (IllegalFormatException e) {
            e.printStackTrace();
            response.sendRedirect("AddSemester");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        this.doPost(request, response);
    }
}
