<%@ page import="com.buaa.mooc.entity.HomeworkSubmit" %>
<%@ page import="java.util.List" %>
<%@ page import="com.buaa.mooc.dao.HomeworkDao" %>
<%@ page import="com.buaa.mooc.entity.Student" %>
<%@ page import="com.buaa.mooc.dao.StudentDao" %>
<%@ page import="com.buaa.mooc.entity.Resource" %>
<%@ page import="com.buaa.mooc.dao.CourseDao" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8" />

    <title>source</title>

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

    <link href="media/css/jquery.fancybox.css" rel="stylesheet" />

    <link href="media/css/jquery.fileupload-ui.css" rel="stylesheet" />

    <!-- END PAGE LEVEL STYLES -->

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

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        资源管理

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="teacher">主页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="TeacherResource">资源管理</a>

                        </li>


                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN EXAMPLE TABLE PORTLET-->

                    <div class="portlet box blue">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-edit"></i>资源列表 </div>

                        </div>

                        <div class="portlet-body">

                            <div class="span5">

                                <a href="AddResource"><span class="btn green fileinput-button">

									<i class="icon-plus icon-white"></i>

									<span>添加资源</span>

                                    <!--<input type="file" name="files[]" multiple> -->

									</span></a>

                            </div>

                            <div style="padding:25px"></div>

                            <table class="table table-striped table-hover table-bordered" id="sample_editable_1">

                                <thead>

                                <tr>

                                    <th>名称</th>

                                    <th>课程</th>

                                    <th>日期</th>

                                    <th>Delete</th>

                                </tr>

                                </thead>

                                <tbody>
                                <%  List<Resource> resources = (List<Resource>)request.getAttribute("resources"); %>
                                <%
                                    if (resources != null && resources.size()>0) {
                                        for (Resource resource :resources) {
                                %>
                                <tr>
                                    <td><%=resource.getRname()%></td>

                                    <td><%=new CourseDao().findByCid(resource.getCid()).getCname()%></td>

                                    <td><%=resource.getAttendTime()%></td>

                                    <td>
                                        <a href="TeacherResourceDel?rid=<%=request.getAttribute("rid")%>" class="btn mini black" style="margin-right:10px"><i class="icon-trash"></i> 删除</a>

                                        <%--<a href="#" class="btn mini green"><i class="icon-download"></i> 下载</a>--%>
                                        <div class="control-group">

                                            <div class="controls">

                                                <a href="TeacherResourceDown?rid=<%=request.getAttribute("rid")%>">
                                                    <label class="control-label" style=" font-weight:bolder">
                                                        下载
                                                    </label></a>

                                            </div>

                                        </div>

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

                    <!-- END EXAMPLE TABLE PORTLET-->

                </div>

            </div>
            <!-- END SAMPLE TABLE PORTLET-->

        </div></div>

    <!-- END PAGE CONTENT-->

</div>

<!-- END PAGE CONTAINER-->

</div>

<!-- END PAGE -->

</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<div class="footer">

    <div class="footer-inner">

        2016 BuaaSoftware Best Group Null

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