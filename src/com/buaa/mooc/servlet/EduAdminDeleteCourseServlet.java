package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huxia on 2017/7/2.
 */
public class EduAdminDeleteCourseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkEduAdmin(request)) {
            response.sendRedirect("/login");
            return;
        }

        try {
            Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
            CourseDao courseDao = new CourseDao();
            courseDao.deleteCourse(cid);
        } catch (Throwable ignored) {
        } finally {
            response.sendRedirect("EduAdminCourse");
        }
    }
}
