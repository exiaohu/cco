package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.TermDao;
import com.buaa.mooc.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huxia on 2017/6/29.
 */
public class DeleteSemesterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkEduAdmin(request)) {
            response.sendRedirect("/login");
        }
        try {
            int term_id = Integer.parseInt(request.getParameterMap().get("termId")[0]);
            TermDao termDao = new TermDao();
            termDao.DeleteTerm(term_id);
            response.sendRedirect("/semester");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/eduadmin");
        }

    }
}
