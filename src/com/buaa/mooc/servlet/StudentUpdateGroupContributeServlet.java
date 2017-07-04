package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupDao;
import com.buaa.mooc.entity.Group;
import com.buaa.mooc.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by huxia on 2017/7/3.
 */
public class StudentUpdateGroupContributeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer gid = Integer.parseInt(request.getParameter("gid"));
            Group group = new GroupDao().findById(gid);
            //response.setCharacterEncoding("utf-8");
            Map<Student, Double> students = new GroupDao().findStudentByGid(gid);
            if (students != null && students.size() > 0) {
                for (Student student : students.keySet()) {
                    Double con = Double.parseDouble(request.getParameter(student.getSid().toString()));
                    students.put(student, con);
                }
                Double total_con = 0.0;
                for (Student student : students.keySet()) {
                    total_con += students.get(student);
                }
                if (Math.abs(total_con - students.size()) < 0.000006) {
                    GroupDao groupDao = new GroupDao();
                    if (!groupDao.UpdateStudentContribute(groupDao.findById(gid), students)) {
                        throw new Exception();
                    } else {
                        /*
                        response.getWriter().write("团队贡献度更新完成！");
                        response.getWriter().flush();
                        response.getWriter().close();*/
                        response.sendRedirect("StudentGroupHome?cid=" + group.getCid());
                    }
                } else {
                    /*
                    response.getWriter().write("错误：所有同学的贡献的总和不为同学的数目。");
                    response.getWriter().flush();
                    response.getWriter().close();*/
                    response.sendRedirect("StudentGroupHome?cid=" + group.getCid());
                }
            }
        } catch (Throwable e) {/*
            e.printStackTrace();
            response.getWriter().write("错误：数据不完整。。");
            response.getWriter().flush();
            response.getWriter().close();*/
            response.sendRedirect("student");
        }
    }
}
