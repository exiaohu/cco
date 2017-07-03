package com.buaa.mooc.servlet;


import com.buaa.mooc.dao.HomeworkDao;

import com.buaa.mooc.dao.TeacherHomeworkCheckDao;
import com.buaa.mooc.entity.Homework;
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
 * Created by lenovo on 2017/7/2.
 */
public class TeacherHomeworkCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        Integer hid = Integer.parseInt(request.getParameterMap().get("hid")[0]);
        TeacherHomeworkCheckDao teacherHomeworkCheckDao = new TeacherHomeworkCheckDao();
        List<HomeworkSubmit> homeworkSubmits = teacherHomeworkCheckDao.findByHid(hid);
        request.setAttribute("homeworkSubmits", homeworkSubmits);
        request.setAttribute("hid",hid);

        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_homework_check.jsp");
        rd.forward(request, response);
    }
}
