package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.StudentCourseDao;
import com.buaa.mooc.entity.StudentCourse;
import com.buaa.mooc.utils.Validation;
import java.io.File;
import java.io.FileInputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2017/7/4.
 */
public class DownExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
        StudentCourseDao studentCourseDao =new StudentCourseDao();
        List<StudentCourse> studentCourses =studentCourseDao.findByCid(cid);
        request.setAttribute("studentCources",studentCourses);

        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/downExcel.jsp");
        rd.forward(request, response);

    }
}
