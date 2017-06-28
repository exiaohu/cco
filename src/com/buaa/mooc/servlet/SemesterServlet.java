package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.TermDao;
import com.buaa.mooc.entity.Term;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by huxia on 2017/6/28.
 */
public class SemesterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkEduAdmin(request)) {
            response.sendRedirect("/login");
            return;
        }

        TermDao termDao = new TermDao();
        List<Term> termList = termDao.FindAll();

        request.setAttribute("terms", termList);

        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/admin_semester.jsp");
        rd.forward(request, response);
    }
}
