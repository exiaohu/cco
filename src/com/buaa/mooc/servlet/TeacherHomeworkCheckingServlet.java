package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.FileDao;
import com.buaa.mooc.dao.HomeworkDao;
import com.buaa.mooc.dao.StudentDao;
import com.buaa.mooc.dao.TeacherHomeworkCheckDao;
import com.buaa.mooc.entity.Homework;
import com.buaa.mooc.entity.HomeworkSubmit;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.IllegalFormatException;

/**
 * Created by lenovo on 2017/7/2.
 */
public class TeacherHomeworkCheckingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int hid = Integer.parseInt(request.getParameterMap().get("hid")[0]);
            int sid = Integer.parseInt(request.getParameterMap().get("sid")[0]);
            System.out.println(hid);
            System.out.println(sid);


            Float score = Float.parseFloat(request.getParameter("score"));
            String remark = new String( request.getParameter("_value").getBytes("iso-8859-1"), "utf-8");
            TeacherHomeworkCheckDao checkDao =new TeacherHomeworkCheckDao();
            checkDao.EditHomeworkCheck(hid,sid,score,remark);
            response.sendRedirect("TeacherHomeworkCheck?hid="+hid);
        } catch (IllegalFormatException e) {
            e.printStackTrace();
            response.sendRedirect("AddSemester");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        int hid = Integer.parseInt(request.getParameterMap().get("hid")[0]);
        int sid = Integer.parseInt(request.getParameterMap().get("sid")[0]);
        request.setAttribute("hid", hid);
        request.setAttribute("sid", sid);
        TeacherHomeworkCheckDao checkDao=new TeacherHomeworkCheckDao();
        HomeworkSubmit submit =checkDao.findByPK(hid,sid);


        request.setAttribute("hName",new HomeworkDao().findByHid(hid).getHomeworkName());
        request.setAttribute("startDate",new HomeworkDao().findByHid(hid).getStartTime());
        request.setAttribute("deadLine",new HomeworkDao().findByHid(hid).getDeadLine());
        request.setAttribute("information",new HomeworkDao().findByHid(hid).getHomeworkInformation());
        request.setAttribute("sName",new StudentDao().findById(sid).getSname());
        request.setAttribute("major",new StudentDao().findById(sid).getMajor());
        request.setAttribute("content",submit.getSubmitContent());
        request.setAttribute("score",submit.getScore());
        request.setAttribute("remark",submit.getRemark());
        request.setAttribute("fid",submit.getFid());
        String fname = new FileDao().getFileById(submit.getFid()).getFilename();
        fname = fname.substring(fname.lastIndexOf("\\") + 1);
        request.setAttribute("fname",fname);

        // <!-- Homework homework = request.getParameter(Homework).getBytes();-->
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_homework_checking.jsp");
        rd.forward(request, response);
    }
}
