package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.dao.StudentJoinGroupDao;
import com.buaa.mooc.entity.GroupRecruit;
import com.buaa.mooc.entity.StudentRecruitView;
import com.buaa.mooc.entity.Student;
import com.buaa.mooc.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 我不承认 on 2017/7/3.
 */
public class TeacherGroupInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        int grid = Integer.parseInt(request.getParameterMap().get("grid")[0]);
        StudentJoinGroupDao studentJoinGroupDao = new StudentJoinGroupDao();
        List<Student> studentInGroup = studentJoinGroupDao.findByGridwithMemName(grid);
        GroupRecruit gr = new GroupRecruitDao().findByGridGR(grid);
        request.setAttribute( "students",studentInGroup);
        request.setAttribute("GroupRecruit", gr);
        request.getRequestDispatcher( "/teacher_groupInfo.jsp").forward(request,response);
    }
}
