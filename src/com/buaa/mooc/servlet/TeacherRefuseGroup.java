package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.dao.MessageDao;
import com.buaa.mooc.entity.GroupRecruit;
import com.buaa.mooc.entity.StudentRecruitView;
import com.buaa.mooc.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 我不承认 on 2017/7/3.
 */
public class TeacherRefuseGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int grid = Integer.parseInt(request.getParameter("grid"));
        String reason = request.getParameter("reason");
        GroupRecruitDao groupRecruitDao = new GroupRecruitDao();
        List<StudentRecruitView> students = groupRecruitDao.findByGridSRV(grid);
        GroupRecruit gr = groupRecruitDao.findByGridGR(grid);
        Integer cid = gr.getCid();
        MessageDao messageDao = new MessageDao();
        for (StudentRecruitView stu : students) {
            messageDao.InsertMessage(stu.getPk().getSid(),
                    "你向队伍["+gr.getGroup_name()+"]的组队申请已被老师拒绝。" +
                            "原因是["+reason+"]。");
        }
        groupRecruitDao.deleteByGridGR(grid);
        response.sendRedirect("TeacherManGroup?cid=" + cid);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        doPost(request,response);
    }
}
