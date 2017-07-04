<%@ page import="com.buaa.mooc.entity.Homework" %>
<%@ page import="java.util.List" %>
<%@ page import="com.buaa.mooc.entity.Student" %>
<%@ page import="com.buaa.mooc.entity.Course" %>
<%@ page import="com.buaa.mooc.entity.StudentCourse" %>
<%@ page import="com.buaa.mooc.dao.*" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- BEGIN HEAD -->

<%--<html xmlns:x="urn:schemas-microsoft-com:office:excel">--%>

<%--<script type="text/javascript">--%>
    <%--function exportExcel(){--%>
        <%--window.open('teacher_grade_all.jsp?exportToExcel=YES');--%>
    <%--}--%>

<%--</script>--%>

<head>

    <meta charset="utf-8" />

    <title>学生课程成绩</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->

    <link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/style.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <!-- END GLOBAL MANDATORY STYLES -->

    <link rel="shortcut icon" href="media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<!-- BEGIN HEADER -->

<jsp:include page="teacher_header.jsp"/>

<!-- BEGIN CONTAINER -->

<div class="page-container row-fluid">

    <jsp:include page="teacher_sidebar.jsp" />
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->

    <div class="page-content">

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        该成绩管理 <small>教师成绩管理页面</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="teacher">主页</a>

                            <i class="icon-angle-right"></i>

                            <a href="TeacherGrade">成绩管理</a>

                            <i class="icon-angle-right"></i>

                            <a href="TeacherGradeAll?cid=<%=request.getAttribute("cid")%>&download=no">该科成绩管理</a>

                        </li>

                    </ul>


                    <!-- END PAGE TITLE & BREADCRUMB-->


                    <!-- BEGIN EXAMPLE TABLE PORTLET-->

                    <div class="portlet box blue">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-edit"></i>学生成绩名单 </div>

                        </div>

                        <div class="portlet-body">

                            <div style="padding:15px"></div>

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

                                    <td><%=groupScore.get(studentCourse.getGid()) * studentCourse.getGroup_contribute()%></td>


                                </tr>
                                <%
                                        }
                                    }
                                %>

                                </tbody>

                            </table>

                            <a href="TeacherGradeAll?cid=<%=request.getAttribute("cid")%>&download=yes" class="btn green" style="width:100px;height:25px;margin-top:10px; margin-left:850px;  margin-right:10px; margin-bottom:10px;">
                                导出学生成绩</a>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

</div>

                            <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

                            <!-- BEGIN CORE PLUGINS -->

                            <script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

                            <script src="media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

                            <!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

                            <script src="media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

                            <script src="media/js/bootstrap.min.js" type="text/javascript"></script>

                            <!--[if lt IE 9]>

                            <script src="media/js/excanvas.min.js"></script>

                            <script src="media/js/respond.min.js"></script>

                            <![endif]-->

                            <script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

                            <script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>

                            <script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>

                            <script src="media/js/jquery.uniform.min.js" type="text/javascript" ></script>

                            <!-- END CORE PLUGINS -->

                            <!-- BEGIN PAGE LEVEL SCRIPTS -->

                            <script src="media/js/app.js" type="text/javascript"></script>

                            <script src="media/js/index.js" type="text/javascript"></script>

                            <!-- END PAGE LEVEL SCRIPTS -->

                            <script>

                                jQuery(document).ready(function() {

                                    App.init(); // initlayout and core plugins
                                });

                            </script>

                            <!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->

</html>