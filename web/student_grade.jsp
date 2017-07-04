<%@ page import="com.buaa.mooc.entity.Course" %>
<%@ page import="com.buaa.mooc.dao.StudentCourseDao" %>
<%@ page import="com.buaa.mooc.entity.StudentCourse" %>
<%@ page import="com.buaa.mooc.entity.Student" %>
<%@ page import="com.buaa.mooc.dao.StudentDao" %><%--
  Created by IntelliJ IDEA.
  User: windrises
  Date: 2017/7/3
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>


<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->

<!-- BEGIN HEAD -->

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

<jsp:include page="student_header.jsp"/>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container">

    <!-- BEGIN SIDEBAR -->

    <jsp:include page="student_sidebar.jsp"/>
    <% Course course = (Course) request.getAttribute("course"); %>

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

                        个人成绩管理 <small>个人成绩查看页面</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="student">主页</a>

                            <i class="icon-angle-right"></i>

                            <a href="StudentCourse?cid=<%=course.getCid()%>"><%=course.getCname()%>
                            </a>

                            <i class="icon-angle-right"></i>

                            <a href="StudentGrade">成绩管理</a>

                        </li>

                    </ul>


                    <!-- END PAGE TITLE & BREADCRUMB-->


                    <!-- BEGIN EXAMPLE TABLE PORTLET-->

                    <div class="portlet box blue">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-edit"></i>个人成绩 </div>

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

                                <%  Integer sid = (Integer) request.getSession().getAttribute("sid");
                                    StudentCourseDao studentCourseDao = new StudentCourseDao();
                                    StudentCourse studentCourse = studentCourseDao.findBySidAndCid(sid,course.getCid());
                                    if(studentCourse.getScore() != null){
                                        Double groupScore = studentCourse.getScore();
                                        Double contribute = studentCourse.getGroup_contribute();
                                        Double score = studentCourse.getScore()*studentCourse.getGroup_contribute();
                                %>

                                        <tr class="">

                                            <td><%=sid%></td>

                                            <td><%=studentCourse.getGid()%></td>

                                            <td><%=new StudentDao().findById(sid).getSname()%></td>

                                            <td><%=groupScore%></td>

                                            <td><%=contribute%></td>

                                            <td><%=score.intValue()%></td>

                                        </tr>
                                <%
                                    }
                                    else{

                                 %>
                                        <tr class="">

                                            <td><%=sid%></td>

                                            <td></td>

                                            <td></td>

                                            <td></td>

                                            <td></td>

                                            <td>暂未成绩</td>

                                            </tr>
                                <%
                                    }
                                %>

                                </tbody>

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!-- END SAMPLE TABLE PORTLET-->

<!-- END BORDERED TABLE PORTLET--><!-- END PAGE HEADER-->

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
    }

</script>

<!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->

</html>
