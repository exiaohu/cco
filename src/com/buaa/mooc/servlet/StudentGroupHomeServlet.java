package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.dao.GroupDao;
import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.dao.StudentCourseDao;
import com.buaa.mooc.entity.*;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huxia on 2017/7/2.
 */
public class StudentGroupHomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("/login");
            return;
        }
        try {
            Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
            Integer sid = (Integer) request.getSession().getAttribute("sid");

            CourseDao courseDao = new CourseDao();
            StudentCourseDao studentCourseDao = new StudentCourseDao();
            GroupDao groupDao = new GroupDao();
            GroupRecruitDao groupRecruitDao = new GroupRecruitDao();
            Course course = courseDao.findByCid(cid);
            StudentCourse studentCourse = studentCourseDao.findBySidAndCid(sid, cid);
            Integer gid = studentCourse.getGid();
            Group group = groupDao.findById(gid);
            StudentRecruitView studentRecruitView = groupRecruitDao.findBySidandCidSRV(sid, cid);

            request.setAttribute("group", group);
            request.setAttribute("course", course);
            request.setAttribute("studentRecruitView", studentRecruitView);

            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_group_homepage.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
