package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.FileDao;
import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * Created by windrises on 2017/7/3.
 */
public class DownloadFileServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("login");
            return;
        }
        Integer fid = Integer.valueOf(request.getParameter("fid"));
        String fullPath = getServletContext().getRealPath("");
        FileDao fileDao = new FileDao();
        String fileName = fileDao.getFileById(fid).getFilename();
        fullPath += fileName;
        System.out.println(fullPath);
        File file = new File(fullPath);
        if(!file.exists()){
            return;
        }
        String realname = fileName.substring(fileName.lastIndexOf("\\") + 1);
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        FileInputStream in = new FileInputStream(fullPath);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while((len=in.read(buffer))>0){
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
        //response.sendRedirect("student");
    }

}
