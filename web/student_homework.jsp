<%@ page import="com.buaa.mooc.entity.Course" %>
<%@ page import="com.buaa.mooc.entity.Homework" %>
<%@ page import="java.util.List" %>
<%@ page import="com.buaa.mooc.dao.StudentHWSubmitDao" %>
<%@ page import="com.buaa.mooc.entity.HomeworkSubmit" %>
<%@ page import="com.buaa.mooc.dao.StudentCourseDao" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8"/>

    <title>作业管理</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="" name="description"/>

    <meta content="" name="author"/>

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

    <!-- BEGIN PAGE LEVEL STYLES -->

    <link rel="stylesheet" href="media/css/DT_bootstrap.css"/>

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="media/image/favicon.ico"/>

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<jsp:include page="student_header.jsp"/>

<!-- BEGIN CONTAINER -->

<div class="page-container row-fluid">

    <jsp:include page="student_sidebar.jsp"/>

    <% Course course = (Course) request.getAttribute("course"); %>

    <!-- BEGIN PAGE -->

    <div class="page-content">

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        作业管理
                        <small>课程作业提交</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="teacher">主页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="StudentCourse?cid=<%=course.getCid()%>"><%=course.getCname()%>
                            </a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="StudentHomework?cid=<%=course.getCid()%>">作业管理</a>

                        </li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN SAMPLE TABLE PORTLET-->

                    <div class="portlet box green">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-bell"></i>作业列表</div>

                        </div>

                        <div class="portlet-body">

                            <table class="table table-striped table-bordered table-advance table-hover">

                                <tr style="align: center">

                                    <th><i class="icon-file-text"></i> 作业名称</th>

                                    <th class="hidden-phone"><i class="icon-time"></i> 开始时间</th>

                                    <th><i class="icon-bell"></i> 截止时间</th>

                                    <th>已提交/最多提交</th>

                                    <th>得分(最高分)</th>

                                    <th>操作</th>

                                </tr>

                                <tbody>

                                <% List<Homework> homeworks = (List<Homework>) request.getAttribute("homeworks");
                                    StudentHWSubmitDao studentHWSubmitDao = new StudentHWSubmitDao();
                                    Integer sid = (Integer) request.getSession().getAttribute("sid");
                                    Integer gid = new StudentCourseDao().findBySidAndCid(sid,course.getCid()).getGid();
                                    //System.out.println(sid);
                                %>

                                <%
                                    if (homeworks != null && homeworks.size() > 0) {
                                        for (Homework homework : homeworks) {
                                            HomeworkSubmit homeworkSubmit = studentHWSubmitDao.findHKSubmitByHidSid(homework.getId(),gid);
                                            Integer submitTimes = null;
                                            if(homeworkSubmit == null){
                                                submitTimes = 0;
                                                //System.out.println("-------------");
                                            }
                                            else{
                                                submitTimes = homeworkSubmit.getSubmitTimes();
                                                //System.out.println("-------" + submitTimes);
                                            }
                                            Integer submitMaxTimes = homework.getSubmitMaxTimes();
                                            String score = null;
                                            if(homeworkSubmit == null || homeworkSubmit.getIsCorrect() == 0){
                                                score = "未批改";
                                            }
                                            else if(homeworkSubmit.getIsCorrect() == 1){
                                                score = homeworkSubmit.getScore().toString();
                                            }
                                %>
                                <tr class="">

                                    <td>
                                        <%=homework.getHomeworkName()%>
                                    </td>

                                    <td>
                                        <%=homework.getStartTime().toLocalDateTime().toString()%>
                                    </td>

                                    <td>
                                        <%=homework.getDeadLine().toLocalDateTime().toString()%>
                                    </td>

                                    <td><%=submitTimes%>/<%=submitMaxTimes%></td>

                                    <td><%=score%></td>

                                    <td><a href="StudentHomeworkView?cid=<%=course.getCid()%>&hid=<%=homework.getId()%>"
                                           class="btn mini green"><i class="icon-eye-open"></i> 查看</a>

                                </tr>
                                <%
                                        }
                                    }
                                %>

                                </tbody>

                            </table>

                        </div>

                    </div>

                    <!-- END SAMPLE TABLE PORTLET-->

                </div>

                <!-- END SAMPLE TABLE PORTLET-->

            </div>

        </div>

        <!-- END PAGE CONTENT-->

    </div>

    <!-- END PAGE CONTAINER-->

</div>


<!-- END CONTAINER -->

<jsp:include page="footer.jsp"/>

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

<script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<script src="media/js/app.js"></script>

<script>

    jQuery(document).ready(function () {

        // initiate layout and plugins

        App.init();

    });

</script>

</body>

<!-- END BODY -->

</html>