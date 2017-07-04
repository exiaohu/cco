<%@ page import="java.util.List" %>
<%@ page import="com.buaa.mooc.entity.Course" %>
<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en" class="no-js"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8"/>

    <title>团队管理页面</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

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

    <link rel="shortcut icon" href="media/image/favicon.ico"/>

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<!-- BEGIN HEADER -->

<jsp:include page="teacher_header.jsp"/>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container">

    <!-- BEGIN SIDEBAR -->

    <jsp:include page="teacher_sidebar.jsp"/>

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

                        团队管理
                        <small>团队管理页面</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="teacher">主页</a>

                            <i class="icon-angle-right"></i>

                            <a href="TeacherGroup">团队管理</a>

                        </li>

                    </ul>


                    <!-- END PAGE TITLE & BREADCRUMB-->


                    <!-- BEGIN EXAMPLE TABLE PORTLET-->

                    <div class="portlet box purple">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-edit"></i>课程列表</div>

                        </div>

                        <div class="portlet-body">

                            <div style="padding:15px"></div>

                            <table class="table table-striped table-hover table-bordered" id="sample_editable_1">

                                <thead>

                                <tr>

                                    <th>课程名</th>

                                    <th>学分</th>

                                    <th>开课时间</th>

                                    <th>上课地址</th>

                                    <th>团队信息</th>

                                </tr>

                                </thead>

                                <tbody>

                                <%
                                    List<Course> courses = (List<Course>) request.getAttribute("course");
                                    if (courses != null && courses.size() > 0) {
                                        for (Course course : courses) {
                                %>

                                <tr class="">

                                    <td><%=course.getCname()%></td>

                                    <td><%=course.getCredit()%></td>

                                    <td><%=course.getBeginDate().toLocalDate().toString()%></td>

                                    <td><%=course.getAddress()%></td>

                                    <td>

                                        <a href="TeacherManGroup?cid=<%=course.getCid()%>" class="btn mini green">

                                            查看团队列表

                                        </a>

                                    </td>

                                </tr>

                                <%
                                        }
                                    }
                                %>

                                </tbody>

                            </table>

                        </div>

                    </div>

                </div>

            </div>

        </div>

        <!-- END SAMPLE TABLE PORTLET-->

    </div>

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

    <script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>

    <!-- END CORE PLUGINS -->

    <!-- BEGIN PAGE LEVEL SCRIPTS -->

    <script src="media/js/app.js" type="text/javascript"></script>

    <script src="media/js/index.js" type="text/javascript"></script>

    <!-- END PAGE LEVEL SCRIPTS -->

    <script>

        jQuery(document).ready(function () {
            App.init(); // initlayout and core plugins
        });

    </script>

    <!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->

</html>