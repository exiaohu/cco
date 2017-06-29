package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.HomeworkDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.IllegalFormatException;

/**
 * Created by huxia on 2017/6/29.
 */
public class AddHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer _limit = Integer.parseInt(request.getParameter("_limit"));
            String hname = new String( request.getParameter("assign_name").getBytes("iso-8859-1"), "utf-8");
            ParsePosition pos = new ParsePosition(0);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date startDate = formatter.parse(request.getParameter("startDate"), pos);
            formatter = new SimpleDateFormat("MM/dd/yyyy");
            pos = new ParsePosition(0);
            java.util.Date endDate = formatter.parse(request.getParameter("endDate"), pos);

            HomeworkDao homeworkDao = new HomeworkDao();
            homeworkDao.AddHomework(1, hname, new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()), _limit);

            response.sendRedirect("/TeacherHomework");
        } catch (IllegalFormatException e) {
            e.printStackTrace();
            response.sendRedirect("/AddSemester");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_homework_new.jsp");
        rd.forward(request, response);
    }
}