package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.dao.GroupRecruitDao;
import com.buaa.mooc.entity.Course;
import com.buaa.mooc.entity.GroupRecruitView;
import com.buaa.mooc.entity.StudentRecruitView;
import com.buaa.mooc.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by huxia on 2017/7/3.
 */
public class SubmitGroupRecruitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("login");
            return;
        }
        try {
            Integer grid = Integer.parseInt(request.getParameterMap().get("grid")[0]);
            GroupRecruitDao groupRecruitDao = new GroupRecruitDao();
            List<StudentRecruitView> studentRecruitViews = groupRecruitDao.findByGridSRV(grid);
            GroupRecruitView groupRecruitView = groupRecruitDao.findByGridwithMemCount(grid);
            Course course = new CourseDao().findByCid(groupRecruitView.getCid());
            boolean flag = true;
            for (StudentRecruitView student : studentRecruitViews) {
                if (student.getGranted().equals(0)) {
                    flag = false;
                }
            }
            if ((course.getGroup_mem_max() != null && groupRecruitView.getS_count() > course.getGroup_mem_max()) ||
                    (course.getGroup_mem_min() != null && groupRecruitView.getS_count() < course.getGroup_mem_min())) {
                flag = false;
            }
            if (flag) {
                groupRecruitDao.submitGR(grid);
            }
            response.sendRedirect("StudentGroupCheck?grid=" + grid);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.sendRedirect("student");
        }
    }
}
