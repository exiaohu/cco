package com.buaa.mooc.servlet;

import com.buaa.mooc.Service;
import com.buaa.mooc.entity.EduAdmin;
import com.buaa.mooc.entity.Student;
import com.buaa.mooc.entity.Teacher;
import com.buaa.mooc.entity.User;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.IllegalFormatException;

/**
 * Created by 李箫 on 2017/6/27
 */
public class LoginServlet extends javax.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;
    private Service service = new Service();

    public LoginServlet() {
        super();
    }

    private boolean checkLoginState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validation.checkStudent(request)) {
            response.sendRedirect("/student");
            return false;
        }
        if (Validation.checkTeacher(request)) {
            response.sendRedirect("/teacher");
            return false;
        }
        if (Validation.checkEduAdmin(request)) {
            response.sendRedirect("/eduadmin");
            return false;
        }
        return true;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkLoginState(request, response)) {
            return;
        }
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/login.html");
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("username"));
            String password = (String) request.getParameter("password");
            User user = service.login(id, password);
            String uri = null;

            // 验证非法
            if (user == null) {
                response.sendRedirect("login?message=wrongIdorPassword");
            } else if (user instanceof Student) {
                // 学生用户
                request.getSession().setAttribute("sid", id);
                request.getSession().setAttribute("sname", ((Student) user).getSname());
                response.sendRedirect("/student");
            } else if (user instanceof Teacher) {
                // 教师用户
                request.getSession().setAttribute("tid", id);
                request.getSession().setAttribute("tname", ((Teacher) user).getTname());
                response.sendRedirect("/teacher");
            } else if (user instanceof EduAdmin) {
                // 教务用户
                request.getSession().setAttribute("eid", id);
                request.getSession().setAttribute("ename", ((EduAdmin) user).getEname());
                response.sendRedirect("/eduadmin");
            }
        } catch (IllegalFormatException e) {
            // 用户名无法转换为数字
            e.printStackTrace();
            response.sendRedirect("/login");
        }
    }
}
