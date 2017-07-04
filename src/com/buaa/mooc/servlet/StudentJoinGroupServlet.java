package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.entity.GroupRecruitView;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by huxia on 2017/7/2.
 */
public class StudentJoinGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("/login");
            return;
        }
        try {
            Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
            GroupRecruitDao groupRecruitDao = new GroupRecruitDao();
            List<GroupRecruitView> groupRecruitView = groupRecruitDao.findByCidwithMemCount(cid);

            request.setAttribute("groupRecruitView", groupRecruitView);

            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_group_joinApl.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
