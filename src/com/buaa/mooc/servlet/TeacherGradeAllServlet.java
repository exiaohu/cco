package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.dao.HomeworkDao;
import com.buaa.mooc.entity.Course;
import com.buaa.mooc.entity.Homework;
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
@WebServlet(name = "TeacherGradeAllServlet")
public class TeacherGradeAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
        HomeworkDao homeworkDao = new HomeworkDao();
        List<Homework> homeworks =  homeworkDao.findByCid(cid);
        request.setAttribute("homeworks",homeworks);

        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_grade_all.jsp");
        rd.forward(request, response);
    }
}
