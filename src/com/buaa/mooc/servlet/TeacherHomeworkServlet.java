package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.HomeworkDao;
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
 * Created by huxia on 2017/6/29.
 */
public class TeacherHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        Integer tid = (Integer) request.getSession().getAttribute("tid");
        HomeworkDao homeworkDao = new HomeworkDao();
        List<Homework> homeworks = homeworkDao.findByTid(tid);
        request.setAttribute("homeworks", homeworks);

        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_homework.jsp");
        rd.forward(request, response);
    }
}
