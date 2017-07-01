package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.entity.Course;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by huxia on 2017/7/1.
 */
public class EduAdminCourseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkEduAdmin(request)) {
            response.sendRedirect("/login");
            return;
        }

        CourseDao courseDao = new CourseDao();
        List<Course> courses = courseDao.FindAll();
        courses.sort((o1, o2) -> ((o1.getTermId() < o2.getTermId()) ? 1 : 0));

        request.setAttribute("courses", courses);

        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/admin_course.jsp");
        rd.forward(request, response);
    }
}
