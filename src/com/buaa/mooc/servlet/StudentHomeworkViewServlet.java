package com.buaa.mooc.servlet;

import com.buaa.mooc.dao.CourseDao;
import com.buaa.mooc.dao.FileDao;
import com.buaa.mooc.dao.HomeworkDao;
import com.buaa.mooc.dao.StudentHWSubmitDao;
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

        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(2048*1024);
            File file = null;
            String apd[] = {"upload",String.valueOf(cid),String.valueOf(hid),String.valueOf(sid)};
            for(String str:apd) {
                savePath += "/" + str;
                file = new File(savePath);
                //System.out.println(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
            }

            factory.setRepository(file);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            upload.setSizeMax(2048*1024*1024);
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem item : fileItems) {
                if (!item.isFormField()) {
                    filename = item.getName();
                    filename = filename.substring(filename.lastIndexOf("\\") + 1, filename.length());
                    File uploadFile = new File(savePath + "/" + filename);
                    item.write(uploadFile);
                }
                else if (item.getFieldName().equals("studentHWText")) {
                    submitContent = item.getString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileDao fileDao = new FileDao();
        Integer fid = fileDao.AddFile(filename, (Integer) request.getSession().getAttribute("sid"));
        StudentHWSubmitDao submitDao = new StudentHWSubmitDao();
        HomeworkSubmit homeworkSubmit = submitDao.findHKSubmitByHidSid(hid,sid);
        if(homeworkSubmit == null) {
            submitDao.addHWSubmit(hid, sid, fid, now,
                     submitContent, 0, null, null, 1);
            response.sendRedirect("/StudentHomework?cid="+ cid);
        }
        else if(homeworkSubmit.getSubmitTimes() < homework.getSubmitMaxTimes()){
            submitDao.editHWSubmit(hid,sid,fid, now,
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
            StudentHWSubmitDao submitDao = new StudentHWSubmitDao();
            HomeworkSubmit homeworkSubmit = submitDao.findHKSubmitByHidSid(hid,sid);
            HomeworkDao homeworkDao = new HomeworkDao();
            Homework homework = homeworkDao.findByHid(hid);

            if(homeworkSubmit != null){
                request.setAttribute("submitContent", homeworkSubmit.getSubmitContent());
            }
            else{
                request.setAttribute("submitContent", "");
            }
            request.setAttribute("homework", homework);
            request.setAttribute("course", new CourseDao().findByCid(homework.getCourseId()));
            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/student_hwview.jsp");
            rd.forward(request, response);

        } catch (Throwable e) {
            e.printStackTrace();
            response.sendRedirect("student");
        }
    }
}
