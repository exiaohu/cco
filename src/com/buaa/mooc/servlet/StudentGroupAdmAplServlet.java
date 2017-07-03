package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.dao.StudentJoinGroupDao;
import com.buaa.mooc.entity.GroupRecruit;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huxia on 2017/7/3.
 */
public class StudentGroupAdmAplServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer sid = (Integer) request.getSession().getAttribute("sid");
            Integer cid = Integer.parseInt(request.getParameter("cid"));
            String group_name = new String(request.getParameter("group_name").getBytes("iso-8859-1"), "utf-8");
            String information = new String(request.getParameter("recruit_information").getBytes("iso-8859-1"), "utf-8");

            GroupRecruitDao groupRecruitDao = new GroupRecruitDao();
            StudentJoinGroupDao studentJoinGroupDao = new StudentJoinGroupDao();
            GroupRecruit groupRecruit = groupRecruitDao.AddGroupRecruit(sid, group_name, information, cid);
            studentJoinGroupDao.AddRelationSGR(sid, groupRecruit.getGrid());
            response.sendRedirect("StudentGroupHome?cid=" + cid);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("/login");
            return;
        }
        try {
            Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);

            request.setAttribute("cid", cid);

            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_group_admApl.jsp");
            rd.forward(request, response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
