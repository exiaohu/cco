package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.dao.MessageDao;
import com.buaa.mooc.dao.StudentJoinGroupDao;
import com.buaa.mooc.entity.GroupRecruit;
import com.buaa.mooc.entity.Message;
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
            MessageDao messageDao = new MessageDao();
            Integer grid = Integer.parseInt(request.getParameter("grid"));
            Integer sid = Integer.parseInt(request.getParameter("sid"));
            GroupRecruit gr = new GroupRecruitDao().findByGridGR(grid);
            if (message.equalsIgnoreCase("y")) {
                StudentJoinGroupDao studentJoinGroupDao = new StudentJoinGroupDao();
                studentJoinGroupDao.AgreeJoin(grid, sid);
                messageDao.InsertMessage(Integer.parseInt(request.getParameter("sid")), "你向队伍["+gr.getGroup_name()+"]的组队申请已被队伍负责人批准。");
            } else {
                StudentJoinGroupDao studentJoinGroupDao = new StudentJoinGroupDao();
                messageDao.InsertMessage(Integer.parseInt(request.getParameter("sid")), "你向队伍["+gr.getGroup_name()+"]的组队申请已被队伍负责人拒绝。");
                studentJoinGroupDao.DisAgreeJoin(grid, sid);
            }
        } catch (IllegalFormatException e) {
            e.printStackTrace();
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
