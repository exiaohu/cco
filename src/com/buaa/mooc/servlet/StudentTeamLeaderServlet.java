package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.dao.StudentJoinGroupDao;
import com.buaa.mooc.entity.GroupRecruit;
import com.buaa.mooc.entity.StudentRecruitView;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by zhwpc on 2017/7/3.
 */
public class StudentTeamLeaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        try {
            String message = request.getParameter("message");
            if (message.equalsIgnoreCase("y")) {
                StudentJoinGroupDao studentJoinGroupDao = new StudentJoinGroupDao();
                studentJoinGroupDao.AgreeJoin(Integer.parseInt(request.getParameter("grid")), Integer.parseInt(request.getParameter("sid")));
            } else {
                StudentJoinGroupDao studentJoinGroupDao = new StudentJoinGroupDao();
                studentJoinGroupDao.DisAgreeJoin(Integer.parseInt(request.getParameter("'grid")), Integer.parseInt(request.getParameter("sid")));
            }
            response.getWriter().print(true);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IllegalFormatException e) {
            e.printStackTrace();
            response.getWriter().print(true);
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("/login");
            return;
        }
        try {
            Integer grid = Integer.parseInt(request.getParameterMap().get("grid")[0]);
            Integer sid = (Integer) request.getSession().getAttribute("sid");

            GroupRecruitDao groupRecruitDao = new GroupRecruitDao();
            List<StudentRecruitView> students = groupRecruitDao.findByGridSRV(grid);
            GroupRecruit groupRecruit = groupRecruitDao.findByGridGR(grid);

            request.setAttribute("students", students);
            request.setAttribute("groupRecruit", groupRecruit);

            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_TeamLeader.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
