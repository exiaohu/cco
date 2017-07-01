package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.StudentDao;
import com.buaa.mooc.entity.Student;

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
public class AddStudentInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);

            StudentDao studentDao = new StudentDao();
            List<Student> students = studentDao.findByCourseId(cid);

            request.setAttribute("students", students);

            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/admin_addstudent_info.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("/eduadmin");
        }

    }
}
