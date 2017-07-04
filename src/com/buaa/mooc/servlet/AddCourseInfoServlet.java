package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.dao.FileDao;
import com.buaa.mooc.utils.Validation;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by huxia on 2017/6/28.
 */
public class AddCourseInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String outline = null;
        Integer cid = null;
        String filename = null;

        // 上传文件的保存目录
        String savePath = getServletContext().getRealPath("/upload");

        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(2048 * 1024);
            factory.setRepository(new File(savePath));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            upload.setSizeMax(1024 * 1024 * 1024);
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem item : fileItems) {
                if (!item.isFormField()) {
                    filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\") + 1, filename.length());
                    File uploadFile = new File(savePath + "/" + filename);
                    item.write(uploadFile);
                } else {
                    if (item.getFieldName().equals("course")) {
                        outline = new String(item.getString().getBytes("iso-8859-1"), "utf-8");
                    }
                    if (item.getFieldName().equals("cid")) {
                        cid = Integer.parseInt(item.getString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileDao fileDao = new FileDao();
        Integer fid = fileDao.AddFile(filename, (Integer) request.getSession().getAttribute("tid"));
        CourseDao courseDao = new CourseDao();
        courseDao.editCourse(cid, outline, fid);

        response.sendRedirect("/teacherCourse");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        int cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
        request.setAttribute("cid", cid);
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_addcourse_info.jsp");
        rd.forward(request, response);
    }
}
