package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.entity.GroupRecruit;
import com.buaa.mooc.entity.GroupRecruitView;
import com.buaa.mooc.utils.HibernateUtils;
import com.buaa.mooc.utils.Validation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by 我不承认 on 2017/7/3.
 */
@WebServlet(name = "TeacherManGroup")
public class TeacherManGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        try {
            int cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
            GroupRecruitDao groupRecruitDao = new GroupRecruitDao();

            Map<GroupRecruitView,String> groupRecruitInfo = groupRecruitDao.findByCidInfo(cid);

            request.setAttribute( "groupRecruitInfo",groupRecruitInfo);
            request.getRequestDispatcher( "/teacher_groupManage.jsp").forward(request,response);
        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("teacher");
        }
    }
}
