package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.*;
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
            Integer hid = Integer.parseInt(request.getParameterMap().get("hid")[0]);
            Integer gid = Integer.parseInt(request.getParameterMap().get("sid")[0]);
            Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);

            Float score = Float.parseFloat(request.getParameter("score"));
            String remark = new String( request.getParameter("_value").getBytes("iso-8859-1"), "utf-8");
            TeacherHomeworkCheckDao checkDao =new TeacherHomeworkCheckDao();
            checkDao.EditHomeworkCheck(hid,gid,score,remark);
            response.sendRedirect("TeacherHomeworkCheck?hid="+hid+"&cid="+cid);
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
        Integer hid = Integer.parseInt(request.getParameterMap().get("hid")[0]);
        Integer gid = Integer.parseInt(request.getParameterMap().get("sid")[0]);
        Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
        request.setAttribute("hid", hid);
        request.setAttribute("sid", gid);
        TeacherHomeworkCheckDao checkDao=new TeacherHomeworkCheckDao();
        HomeworkSubmit submit =checkDao.findByPK(hid,gid);

        request.setAttribute("hName",new HomeworkDao().findByHid(hid).getHomeworkName());
        request.setAttribute("startDate",new HomeworkDao().findByHid(hid).getStartTime());
        request.setAttribute("deadLine",new HomeworkDao().findByHid(hid).getDeadLine());
        request.setAttribute("information",new HomeworkDao().findByHid(hid).getHomeworkInformation());
        request.setAttribute("sName",new GroupDao().findById(gid).getGname());
        request.setAttribute("content",submit.getSubmitContent());
        request.setAttribute("score",submit.getScore());
        request.setAttribute("remark",submit.getRemark());
        request.setAttribute("fid",submit.getFid());
        String fname = new FileDao().getFileById(submit.getFid()).getFilename();
        fname = fname.substring(fname.lastIndexOf("\\") + 1);
        request.setAttribute("fname",fname);
        request.setAttribute("cid",new HomeworkDao().findByHid(hid).getCourseId());

        // <!-- Homework homework = request.getParameter(Homework).getBytes();-->
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_homework_checking.jsp");
        rd.forward(request, response);
    }
}
