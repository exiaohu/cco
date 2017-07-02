package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.*;
import com.buaa.mooc.entity.Course;
import com.buaa.mooc.entity.Student;
import com.buaa.mooc.entity.Teacher;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxia on 2017/7/1.
 */
public class EduAdminCourseInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer cid = Integer.parseInt(request.getParameter("cid"));
            String cname = new String(request.getParameter("cname").getBytes("iso-8859-1"), "utf-8");
            ParsePosition pos = new ParsePosition(0);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date beginDate = formatter.parse(request.getParameter("beginDate"), pos);
            pos = new ParsePosition(0);
            formatter = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date endDate = formatter.parse(request.getParameter("endDate"), pos);
            Integer credit = Integer.parseInt(request.getParameter("credit"));
            String address = new String(request.getParameter("address").getBytes("iso-8859-1"), "utf-8");
            String sids_s = request.getParameter("sids");
            System.out.println(sids_s);
            StudentDao studentDao = new StudentDao();
            String[] sids = sids_s.split(" |\n|,|;");
            if (sids.length > 0) {
                for (String sid_s : sids) {
                    if (sid_s != null && !sid_s.isEmpty()) {
                        try {
                            Integer sid = Integer.parseInt(sid_s);
                            Student student = studentDao.findById(sid);
                            if (student != null) {
                                StudentCourseDao studentCourseDao = new StudentCourseDao();
                                studentCourseDao.AddRelationSC(sid, cid);
                            }
                        } catch (Throwable ignored) {
                        }
                    }
                }
            }

            CourseDao courseDao = new CourseDao();
            TermDao termDao = new TermDao();
            courseDao.editCourseByAdmin(cid, termDao.FindLast().getTermId(), cname, new Date(beginDate.getTime()), new Date(endDate.getTime()), credit, address);

            String[] tids_s = request.getParameterValues("teacher");
            List<Integer> tids_true = new ArrayList<>();
            if (tids_s != null && tids_s.length > 0) {
                for (String tid_s : tids_s) {
                    tids_true.add(Integer.parseInt(tid_s));
                }
            }

            TeacherDao teacherDao = new TeacherDao();
            TeacherCourseDao teacherCourseDao = new TeacherCourseDao();
            List<Integer> tids_all = teacherDao.findAllTid();
            for (Integer tid : tids_all) {
                if (tids_true.contains(tid)) {
                    teacherCourseDao.addRelationTC(cid, tid);
                } else {
                    teacherCourseDao.deleteRelationTC(cid, tid);
                }
            }

            response.sendRedirect("/EduAdminCourse");

        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("/eduadmin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkEduAdmin(request)) {
            response.sendRedirect("/login");
            return;
        }

        try {
            int cid;
            Course course;
            List<Teacher> teachersFalse;
            List<Student> students;
            List<Teacher> teachersTrue;
            List<Integer> tidTrue;
            CourseDao courseDao = new CourseDao();
            ;
            try {
                cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
                course = courseDao.findByCid(cid);
            } catch (Exception e) {
                course = courseDao.addCourse("默认名称", 0, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "", new TermDao().FindLast().getTermId());
                cid = course.getCid();
            }

            TeacherDao teacherDao = new TeacherDao();
            teachersFalse = teacherDao.findAll();
            StudentDao studentDao = new StudentDao();
            students = studentDao.findByCourseId(cid);

            TeacherCourseDao teacherCourseDao = new TeacherCourseDao();
            teachersTrue = teacherCourseDao.findByCid(cid);
            tidTrue = teacherCourseDao.findTidByCid(cid);
            teachersFalse.removeIf((t) -> tidTrue.contains(t.getTid()));

            request.setAttribute("course", course);
            request.setAttribute("students", students);
            request.setAttribute("teachersFalse", teachersFalse);
            request.setAttribute("teachersTrue", teachersTrue);

            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/admin_courseInfo.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/EduAdminCourse");
        }
    }
}
