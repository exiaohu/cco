package com.buaa.mooc.servlet;
import com.buaa.mooc.dao.*;
import com.buaa.mooc.entity.*;
import com.sun.glass.ui.Window;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.buaa.mooc.utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/7/3.
 */
public class TeacherGradeAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Validation.checkTeacher(request)) {
            response.sendRedirect("/login");
            return;
        }
        Integer cid = Integer.parseInt(request.getParameterMap().get("cid")[0]);
        StudentCourseDao studentCourseDao =new StudentCourseDao();
        List<StudentCourse> studentCourses =studentCourseDao.findByCid(cid);
        request.setAttribute("studentCources",studentCourses);
        request.setAttribute("cid",cid);

        String str = request.getParameterMap().get("download")[0];
        if(str.equals("yes")){
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet("学生成绩");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

            HSSFCell cell = row.createCell(0);
            cell.setCellValue("学号");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("组别");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("学生姓名");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("团队成绩");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("团队贡献度");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("个人成绩");
            cell.setCellStyle(style);
            int i = 0;

            if (studentCourses != null && studentCourses.size() > 0) {
                Map<Integer, Double> groupScore = new GroupScoreDao().findByCid(studentCourses.get(0).getPk().getCid());
                for (StudentCourse studentCourse : studentCourses) {

                    Integer sid = studentCourse.getPk().getSid();

                    Integer gid = studentCourse.getGid();

                    String sname = new StudentDao().findById(studentCourse.getPk().getSid()).getSname();

                    Double score = groupScore.get(studentCourse.getGid());

                    Double contribute = studentCourse.getGroup_contribute();

                    Double personScore = groupScore.get(studentCourse.getGid()) * studentCourse.getGroup_contribute();


//       List list = CreateSimpleExcelToDisk.getStudent();

//        for (int i = 0; i < list.size(); i++)
                    {
                        row = sheet.createRow((int) i + 1);

//            Student stu = (Student) list.get(i);
                        // 第四步，创建单元格，并设置值
                        row.createCell((short) 0).setCellValue(sid);
                        row.createCell((short) 1).setCellValue(gid);
                        row.createCell((short) 2).setCellValue(sname);
                        row.createCell((short) 3).setCellValue(score);
                        row.createCell((short) 4).setCellValue(contribute);
                        row.createCell((short) 5).setCellValue(personScore);
                        i++;

//            cell = row.createCell((short) 5);
//            cell.setCellValue();
                    }
                }
            }
            // 第六步，将文件存到指定位置
//        try
//        {
//            FileOutputStream fout = new FileOutputStream("F:/students.xls");
//            wb.write(fout);
//            fout.close();
//
////            FileInputStream in = new FileInputStream("");
////            OutputStream out = response.getOutputStream();
////            byte buffer[] = new byte[1024];
////            int len = 0;
////            while((len=in.read(buffer))>0){
////                out.write(buffer, 0, len);
////            }
////            in.close();
////            out.close();
//////            FileInputStream in = new FileInputStream(fullPath);
//////            OutputStream out = response.getOutputStream();
//////            byte buffer[] = new byte[1024];
//////            int len = 0;
//////            while((len=in.read(buffer))>0){
//////                out.write(buffer, 0, len);
//////            }
//////            in.close();
//////            out.close();
//
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
            String fileName = "学生成绩EXCEL";
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }

        }

        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/teacher_grade_all.jsp");
        rd.forward(request, response);
    }
}
