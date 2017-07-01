package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.StudentDao;
import com.buaa.mooc.entity.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huxia on 2017/7/1.
 */
public class AddStudentInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentIds = request.getParameter("StudentIds");
        try {
            StudentDao studentDao = new StudentDao();
            String[] sids = studentIds.split(" |\n|,|;");
            JSONArray jsonArray = new JSONArray();
            if (sids.length > 0) {
                for (String sid_s : sids) {
                    if (sid_s != null && !sid_s.isEmpty()) {
                        try {
                            Integer sid = Integer.parseInt(sid_s);
                            Student student = studentDao.findById(sid);
                            if (student != null) {
                                JSONObject obj = new JSONObject();
                                obj.put("sname", student.getSname());
                                obj.put("className", student.getClassName());
                                obj.put("sex", student.getSex());
                                obj.put("major", student.getMajor());
                                obj.put("sid", student.getSid());
                                jsonArray.put(obj);
                            }
                        } catch (Throwable ignored) {
                        }
                    }
                }
            }
            response.getWriter().print(jsonArray.toString());
        } catch (Throwable e) {
            e.printStackTrace();
            response.getWriter().print("[]");
        }
    }
}
