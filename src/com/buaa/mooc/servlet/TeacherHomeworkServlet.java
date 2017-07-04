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
//        Integer tid = (Integer) request.getSession().getAttribute("tid");
        Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
        HomeworkDao homeworkDao = new HomeworkDao();
        List<Homework> homeworks = homeworkDao.findByCid(cid);
        request.setAttribute("homeworks", homeworks);
        request.setAttribute("cid",cid);

        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_homework.jsp");
        rd.forward(request, response);
    }
}
