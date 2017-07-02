package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.entity.GroupRecruit;
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
            Map<GroupRecruit, Long> groupRecruitswithMemCount = groupRecruitDao.findByCidwithMemCount(cid);

            request.setAttribute("groupRecruitswithMemCount", groupRecruitswithMemCount);

            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_group_joinApl.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
