package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.dao.FileDao;
import com.buaa.mooc.dao.ResourceDao;
import com.buaa.mooc.utils.Validation;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2017/7/3.
 */
public class AddResourceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String outline = null;
        Integer cid = null;
        String filename = null;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);      //获取当前时间

        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date nowDate = formatter.parse(dateNowStr, pos);

        // 上传文件的保存目录
        String savePath = getServletContext().getRealPath("/upload");

        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(2048 * 1024);
            factory.setRepository(new File(savePath));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(1024 * 1024 * 1024);
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem item : fileItems) {
                if (!item.isFormField()) {
                    filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\") + 1, filename.length());
                    File uploadFile = new File(savePath + "/" + filename);
                    item.write(uploadFile);
                } else {
                    if (item.getFieldName().equals("cid")) {
                        cid = Integer.parseInt(item.getString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResourceDao resourceDao = new ResourceDao();
        Integer rid =resourceDao.AddResource(filename,(Integer)request.getSession().getAttribute("tid"));
        resourceDao.editResource(cid, rid, new Timestamp(nowDate.getTime()));
//        FileDao fileDao = new FileDao();
//        Integer fid = fileDao.AddFile(filename, (Integer) request.getSession().getAttribute("tid"));
//        CourseDao courseDao = new CourseDao();
//        courseDao.editCourse(cid, outline, fid);

        response.sendRedirect("TeacherResource");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
//        int cid = Integer.parseInt(request.getParameterMap().get("rid")[0]);
//        request.setAttribute("rid", cid);
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_resource_new.jsp");
        rd.forward(request, response);

    }
}
