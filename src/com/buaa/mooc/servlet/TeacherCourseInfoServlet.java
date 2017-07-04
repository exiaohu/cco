package com.buaa.mooc.servlet;

        import com.buaa.mooc.dao.CourseDao;
        import com.buaa.mooc.dao.FileDao;
        import com.buaa.mooc.entity.Course;
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
public class TeacherCourseInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/teacherCourse");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        int cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
        request.setAttribute("cid", cid);
        CourseDao courseDao = new CourseDao();
        Course course = courseDao.findByCid(cid);
        request.setAttribute("beginDate",course.getBeginDate());
        request.setAttribute("endDate",course.getEndDate());
        request.setAttribute("outline",course.getOutline());
        String fileName = null;
        try {
            fileName = new FileDao().getFileById(course.getFid()).getFilename();
            System.out.println(fileName);
            //fileName = new String(fileName.getBytes("iso-8859-1"), "utf-8");
        } catch (Throwable ignored) {
        }
        request.setAttribute("accessory", fileName);
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_course_info.jsp");
        rd.forward(request, response);
    }
}
