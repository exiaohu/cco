package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.HomeworkDao;
import com.buaa.mooc.entity.Homework;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatException;
/**
 * Created by lenovo on 2017/7/1.
 */
public class TeacherHomeworkEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer hid = Integer.parseInt(request.getParameter("hid"));

            String imformation = new String( request.getParameter("request").getBytes("iso-8859-1"), "utf-8");
            HomeworkDao homeworkDao = new HomeworkDao();

           // homeworkDao.EditHomework(hid, hname, new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()), imformation);
            homeworkDao.EditHomework(hid,imformation);
            System.out.print(123413);
            response.sendRedirect("TeacherHomework");
        } catch (IllegalFormatException e) {
            e.printStackTrace();
            response.sendRedirect("AddSemester");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int hid = Integer.parseInt(request.getParameterMap().get("hid")[0]);
        request.setAttribute("hid", hid);
        HomeworkDao homeworkDao = new HomeworkDao();
        Homework homework = homeworkDao.findByHid(hid);
        request.setAttribute("hname",homework.getHomeworkName());
        request.setAttribute("startDate",homework.getStartTime());
        request.setAttribute("deadLine",homework.getDeadLine());
        request.setAttribute("imformation",homework.getHomeworkInformation());

       // <!-- Homework homework = request.getParameter(Homework).getBytes();-->
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_homework_edit.jsp");
        rd.forward(request, response);
    }
}
