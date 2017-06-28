package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.TermDao;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.IllegalFormatException;

/**
 * Created by huxia on 2017/6/28.
 */
public class AddSemesterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer year = Integer.parseInt(request.getParameter("course"));
            String quarter = request.getParameter("quarter");
            ParsePosition pos = new ParsePosition(0);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date startDate = formatter.parse(request.getParameter("startDate"), pos);
            formatter = new SimpleDateFormat("MM/dd/yyyy");
            pos = new ParsePosition(0);
            java.util.Date endDate = formatter.parse(request.getParameter("endDate"), pos);
            Integer weeks = Integer.parseInt(request.getParameter("weeks"));

            TermDao termDao = new TermDao();
            System.out.println(startDate + "\n"+ endDate + "\n"+  year+ "\n"+  quarter+ "\n"+ weeks);
            termDao.AddTerm(new Date(startDate.getTime()), new Date(endDate.getTime()), year, quarter, weeks);

            response.sendRedirect("/semester");
        } catch (IllegalFormatException e) {
            e.printStackTrace();
            response.sendRedirect("/AddSemester");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkEduAdmin(request)) {
            response.sendRedirect("/login");
            return;
        }
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/admin_addsemester.jsp");
        rd.forward(request, response);
    }
}
