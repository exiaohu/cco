package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.*;
import com.buaa.mooc.entity.Homework;
import com.buaa.mooc.entity.HomeworkSubmit;
import com.buaa.mooc.entity.HomeworkSubmitPK;
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
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by huxia on 2017/7/1.
 */
public class StudentHomeworkViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submitContent = null;
        String filename = null;

        Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
        Integer hid = Integer.parseInt(request.getParameterMap().get("hid")[0]);
        Integer sid = (Integer) request.getSession().getAttribute("sid");
        Integer gid = new StudentCourseDao().findBySidAndCid(sid,cid).getGid();

        HomeworkDao homeworkDao = new HomeworkDao();
        Homework homework = homeworkDao.findByHid(hid);
        Timestamp startTime = homework.getStartTime();
        Timestamp deadLine = homework.getDeadLine();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(now.before(startTime)){
            response.sendRedirect("/StudentHomeworkView?cid="+ cid + "&hid=" + hid + "&error=early");
            return;
        }
        else if(now.after(deadLine)){
            response.sendRedirect("/StudentHomeworkView?cid="+ cid + "&hid=" + hid + "&error=late");
            return;
        }

        //System.out.println("id = " + cid + " " + hid + " " + sid);

        // 上传文件的保存目录
        String savePath = getServletContext().getRealPath("");
        String path = "";

        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(2048*1024);
            File file = null;
            String apd[] = {"upload","\\" + cid,"\\" + hid,"\\" + gid};
            for(String str:apd) {
                path += str;
                savePath += str;
                file = new File(savePath);
                //System.out.println(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
            }

            factory.setRepository(file);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            upload.setSizeMax(1024*1024*1024);
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem item : fileItems) {
                if (!item.isFormField()) {
                    filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\") + 1, filename.length());
                    String fullPath = savePath + "\\" + filename;
                    //System.out.println(filename + "-" + savePath + "-" + filename);
                    path += "\\" + filename;
                    //System.out.println(path);
                    File uploadFile = new File(fullPath);
                    item.write(uploadFile);
                }
                else if (item.getFieldName().equals("studentHWText")) {
                    submitContent = new String(item.getString().getBytes("iso-8859-1"), "utf-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileDao fileDao = new FileDao();
        Integer fid = fileDao.AddFile(path, (Integer) request.getSession().getAttribute("sid"));
        StudentHWSubmitDao submitDao = new StudentHWSubmitDao();
        HomeworkSubmit homeworkSubmit = submitDao.findHKSubmitByHidSid(hid,gid);
        if(homeworkSubmit == null) {
            submitDao.addHWSubmit(hid, gid, fid, now,
                     submitContent, 0, null, null, 1);
            response.sendRedirect("/StudentHomework?cid="+ cid);
        }
        else if(homeworkSubmit.getSubmitTimes() < homework.getSubmitMaxTimes()){
            submitDao.editHWSubmit(hid, gid, fid, now,
                    submitContent, 0, null, null,
                    homeworkSubmit.getSubmitTimes() + 1);
            response.sendRedirect("/StudentHomework?cid="+ cid);
        }
        else{
            //System.out.println("111111111111111111111111111");
            response.sendRedirect("/StudentHomeworkView?cid="+ cid + "&hid=" + hid + "&error=exceed");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkStudent(request)) {
            response.sendRedirect("login");
            return;
        }
        try {
            Integer sid = (Integer) request.getSession().getAttribute("sid");
            Integer hid = Integer.parseInt(request.getParameterMap().get("hid")[0]);
            Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
            Integer gid = new StudentCourseDao().findBySidAndCid(sid,cid).getGid();
            StudentHWSubmitDao submitDao = new StudentHWSubmitDao();
            HomeworkSubmit homeworkSubmit = submitDao.findHKSubmitByHidSid(hid,gid);
            HomeworkDao homeworkDao = new HomeworkDao();
            Homework homework = homeworkDao.findByHid(hid);
            Timestamp startTime = homework.getStartTime();
            Timestamp deadLine = homework.getDeadLine();
            Timestamp now = new Timestamp(System.currentTimeMillis());
            if(now.before(startTime)){
                request.setAttribute("status","作业还未开始");
            }
            else if(now.after(deadLine)){
                request.setAttribute("status","作业已经结束");
            }
            else{
                request.setAttribute("status","作业正在进行");
            }

            if(homeworkSubmit != null){
                request.setAttribute("submitContent", homeworkSubmit.getSubmitContent());
                request.setAttribute("submitStatus", "已经提交");
                FileDao fileDao = new FileDao();
                String fname = fileDao.getFileById(homeworkSubmit.getFid()).getFilename();
                fname = fname.substring(fname.lastIndexOf("\\") + 1);
                request.setAttribute("fname",fname);
                request.setAttribute("fid",homeworkSubmit.getFid());
            }
            else{
                request.setAttribute("submitContent", "");
                request.setAttribute("submitStatus", "未提交");
                if(homeworkSubmit == null || homeworkSubmit.getIsCorrect() == 0){
                    request.setAttribute("fname","");
                }
                else{
                    request.setAttribute("fname","没有附件");
                }
            }
            request.setAttribute("homework", homework);
            request.setAttribute("course", new CourseDao().findByCid(homework.getCourseId()));
            RequestDispatcher rd = null;
            if(homeworkSubmit == null || homeworkSubmit.getIsCorrect() == 0){
                rd = getServletConfig().getServletContext().getRequestDispatcher("/student_hwview.jsp");
            }
            else{
                request.setAttribute("comment",homeworkSubmit.getRemark());
                rd = getServletConfig().getServletContext().getRequestDispatcher("/student_hw_submitInfo.jsp");
            }
            rd.forward(request, response);

        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
