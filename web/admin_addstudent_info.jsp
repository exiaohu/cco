<%@ page import="java.util.List" %>
<%@ page import="com.buaa.mooc.entity.Student" %>
<%@ page import="java.util.Objects" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8" />

    <title>导入学生</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="" name="description" />

    <meta content="" name="author" />

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

    <link rel="stylesheet" href="media/css/DT_bootstrap.css" />

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="media/image/favicon.ico" />
    <!--<script src="media/js/parse.js"></script>-->

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<jsp:include page="admin_header.jsp" />

<!-- BEGIN CONTAINER -->

<div class="page-container row-fluid">

    <jsp:include page="admin_sidebar.jsp"/>

    <!-- BEGIN PAGE -->

    <div class="page-content">

        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <div id="portlet-config" class="modal hide">

            <div class="modal-header">

                <button data-dismiss="modal" class="close" type="button"></button>

                <h3>portlet Settings</h3>

            </div>

            <div class="modal-body">

                <p>Here will be a configuration form</p>

            </div>

        </div>

        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                        导入学生信息
                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="eduadmin">主页</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li>

                            <a href="EduAdminCourse">课程管理</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li><a href="AddStudentInfo">导入学生信息</a></li>

                    </ul>

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN SAMPLE FORM PORTLET-->

                    <div class="portlet box blue tabbable">

                        <div class="portlet-title">

                            <div class="caption">

                                <i class="icon-reorder"></i>

                                <span class="hidden-480">学生信息</span>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <div class="tabbable portlet-tabs">

                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#" data-toggle="tab">导入</a></li>
                                </ul>

                                <div class="tab-content">

                                    <div class="tab-pane active" id="portlet_tab1">
                                    </div>
                                    <form action="AddStudentInfo" class="form-horizontal" method="post" enctype="multipart/form-data">
                                        <input type="file" name="fileField" class="file" id="files" size="28" onChange="document.getElementById('textfield').value=this.value" />
                                        <div class="portlet-body flip-scroll">

                                            <table class="table-bordered table-striped table-condensed flip-content">

                                                <thead class="flip-content">

                                                <tr>

                                                    <th>学号</th>

                                                    <th>姓名</th>

                                                    <th class="numeric">性别</th>

                                                    <th class="numeric">班级</th>

                                                    <th class="numeric">专业</th>

                                                </tr>

                                                </thead>

                                                <tbody>

                                                <% List<Student> students = (List<Student>) request.getAttribute("students"); %>

                                                <%
                                                    if (students != null && students.size()>0) {
                                                        for (Student student : students) {
                                                %>

                                                <tr>

                                                    <td><%=student.getSid()%>></td>

                                                    <td><%=student.getSname()%></td>

                                                    <td class="numeric"><%=(Objects.equals(student.getSex(), "f") ? "女" : "男")%></td>

                                                    <td class="numeric"><%=student.getClassName()%></td>

                                                    <td class="numeric"><%=student.getMajor()%></td>

                                                </tr>

                                                <%
                                                        }
                                                    }
                                                %>

                                                </tbody>
                                            </table></div>

                                    </form>
                                    <div class="form-actions">

                                        <button type="submit" id="submit" class="btn blue"><i class="icon-ok"></i> Save</button>

                                        <button type="button" class="btn">Cancel</button>

                                    </div>
                                </div>

                                <!-- END PAGE CONTENT-->

                            </div>

                            <!-- END PAGE CONTAINER-->

                        </div>

                        <!-- END PAGE -->

                    </div>
                </div>
            </div>
        </div>

        <!-- END CONTAINER -->

        <!-- BEGIN FOOTER -->

        <div class="footer">

            <div class="footer-inner">

                GAD group 16

            </div>

            <div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

            </div>

        </div>

        <!-- END FOOTER -->

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

        <script src="media/js/app.js"></script>

        <script>

            jQuery(document).ready(function() {

                // initiate layout and plugins

                App.init();
            });
        </script>
</body>

<!-- END BODY -->

</html>