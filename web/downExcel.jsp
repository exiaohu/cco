<%@ page import="com.buaa.mooc.entity.Homework" %>
<%@ page import="java.util.List" %>
<%@ page import="com.buaa.mooc.entity.Student" %>
<%@ page import="com.buaa.mooc.entity.Course" %>
<%@ page import="com.buaa.mooc.entity.StudentCourse" %>
<%@ page import="com.buaa.mooc.dao.*" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html xmlns:x="urn:schemas-microsoft-com:office:excel">

<script type="text/javascript">
    function exportExcel(){
        window.open('downExcel.jsp?exportToExcel=YES');
    }

</script>
<head>
    <title>导出文件</title>
</head>
<body>

<div class="portlet-body">

    <div style="padding:15px"></div>
        <%
                                String exportToExcel = request.getParameter("exportToExcel");
                                if (exportToExcel != null
                                        && exportToExcel.equalsIgnoreCase("YES")) {
                                    response.setContentType("application/vnd.ms-excel");
                                    response.setHeader("Content-Disposition", "inline; filename="
                                            + "excel.xls");

                                }
                            %>

    <table class="table table-striped table-hover table-bordered" id="sample_editable_1">

        <thead>

        <tr>

            <th>学号</th>

            <th>组别</th>

            <th>学生姓名</th>

            <th>团队成绩</th>

            <th>团队贡献度</th>

            <th>个人成绩</th>

        </tr>

        </thead>

        <tbody>

        <%  List<StudentCourse> studentCourses = (List<StudentCourse>)request.getAttribute("studentCources"); %>
        <%
            if (studentCourses != null && studentCourses.size()>0) {
                Map<Integer, Double> groupScore = new GroupScoreDao().findByCid(studentCourses.get(0).getPk().getCid());
                for (StudentCourse studentCourse : studentCourses) {
        %>
        <tr>

            <td><%=studentCourse.getPk().getSid()%></td>

            <td><%=studentCourse.getGid()%></td>

            <td><%=new StudentDao().findById(studentCourse.getPk().getSid()).getSname()%></td>

            <td><%=groupScore.get(studentCourse.getGid())%></td>

            <td><%=studentCourse.getGroup_contribute()%></td>

            <td><%=groupScore.get(studentCourse.getGid())*studentCourse.getGroup_contribute()%></td>


        </tr>
        <%
                }
            }
        %>

        </tbody>

    </table>

    <!-- END SAMPLE TABLE PORTLET-->

    <!-- END BORDERED TABLE PORTLET--><!-- END PAGE HEADER-->
        <%
                            %>
    <a href="javascript:exportExcel();">导出</a>
        <%
                            %>

</body>
</html>
