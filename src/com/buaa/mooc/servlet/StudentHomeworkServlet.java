package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.StudentHomeworkDao;
import com.buaa.mooc.entity.Homework;

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
    private StudentHomeworkDao homeworkDao = new StudentHomeworkDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Homework> homeworks = homeworkDao.getAllHomeworks();
        System.out.println(homeworks.size());
        request.setAttribute("homeworks", homeworks);
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_homework.jsp");
        rd.forward(request, response);
    }
}
