package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupDao;
import com.buaa.mooc.entity.Group;
import com.buaa.mooc.entity.Student;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by huxia on 2017/7/2.
 */
public class StudentMyGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("/login");
            return;
        }
        try {
            Integer gid = Integer.parseInt(request.getParameterMap().get("gid")[0]);

            GroupDao groupDao = new GroupDao();
            Group group = groupDao.findById(gid);
            Map<Student, Double> students = groupDao.findStudentByGid(gid);

            request.setAttribute("group", group);
            request.setAttribute("students", students);

            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_group_mygroup.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
