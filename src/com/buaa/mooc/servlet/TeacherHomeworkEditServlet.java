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
import java.util.IllegalFormatException;

/**
 * Created by lenovo on 2017/7/1.
 */
public class TeacherHomeworkEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer hid = Integer.parseInt(request.getParameter("hid"));
            Integer cid = Integer.parseInt(request.getParameter("cid"));

            String imformation = new String(request.getParameter("request").getBytes("iso-8859-1"), "utf-8");
            HomeworkDao homeworkDao = new HomeworkDao();
            homeworkDao.EditHomework(hid, imformation);

            response.sendRedirect("TeacherHomework?cid="+cid);
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
        int hid = Integer.parseInt(request.getParameterMap().get("hid")[0]);
        request.setAttribute("hid", hid);
        HomeworkDao homeworkDao = new HomeworkDao();
        Homework homework = homeworkDao.findByHid(hid);
        request.setAttribute("hname", homework.getHomeworkName());
        request.setAttribute("startDate", homework.getStartTime());
        request.setAttribute("deadLine", homework.getDeadLine());
        request.setAttribute("imformation", homework.getHomeworkInformation());
        request.setAttribute("cid",homework.getCourseId());

        // <!-- Homework homework = request.getParameter(Homework).getBytes();-->
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_homework_edit.jsp");
        rd.forward(request, response);
    }
}
