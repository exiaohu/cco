<%@ page import="com.buaa.mooc.entity.HomeworkSubmit" %>
<%@ page import="java.util.List" %>
<%@ page import="com.buaa.mooc.dao.HomeworkDao" %>
<%@ page import="com.buaa.mooc.entity.Student" %>
<%@ page import="com.buaa.mooc.dao.StudentDao" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<head>

    <meta charset="utf-8" />

    <title>作业管理</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="" name="description" />

    <meta content="" name="author" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->

    <link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" media="screen"/>

    <link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/style.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/style-responsive.css" rel="stylesheet" type="text/css" />

    <link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->

    <link href="media/css/invoice.css" rel="stylesheet" type="text/css"/>

    <link href="media/css/print.css" rel="stylesheet" type="text/css" media="print"/>

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

            <div class="row-fluid hidden-print">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        作业批改 <small>请为这名学生的作业进行打分与评价</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="teacher">主页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="TeacherHomework">作业管理</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="TeacherHomeworkCheck?hid=<%=request.getAttribute("hid")%>">作业列表</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <a href="TeacherHomeworkChecking?hid=<%=request.getAttribute("hid")%>&sid=<%=request.getAttribute("sid")%>">作业批改</a>

                        </li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->

            <div class="row-fluid invoice">


                    <div class="row-fluid">

                        <div class="span3">

                            <h4>课程：</h4>

                            <ul class="unstyled">

                                <li>软件开发实习</li>

                            </ul>

                        </div>

                        <div class="span4">

                            <h4>作业信息：</h4>

                            <ul class="unstyled">

                                <li><strong>作业名:</strong><%=request.getAttribute("sName")%></li>

                                <li><strong>开始时间：</strong><%=request.getAttribute("startDate")%></li>

                                <li><strong>结束时间：</strong><%=request.getAttribute("deadLine")%></li>

                                <li><strong>详细需求：</strong><%=request.getAttribute("information")%></li>

                            </ul>

                        </div>

                        <div class="span4 invoice-payment">

                            <h4>个人信息：</h4>

                            <ul class="unstyled">

                                <li><strong>姓名：</strong><%=request.getAttribute("hName")%></li>

                                <li><strong>学号：</strong><%=request.getAttribute("sid")%></li>

                                <li><strong>学院：</strong><%=request.getAttribute("major")%></li>

                            </ul>

                        </div>

                    </div>

                    <div class="row-fluid">

                        <div class="span12">

                        <div class="portlet box yellow">

                                <div class="portlet-title">

                                    <div class="caption"><i class="icon-pencil"></i>作业详情</div>

                                    <div class="tools">

                                        <a href="javascript:" class="collapse"></a>

                                        <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                        <a href="javascript:" class="reload"></a>

                                        <a href="javascript:" class="remove"></a>

                                    </div>

                                </div>

                                <div class="portlet-body">

                                    <div>

                                        <%--<h3><strong>查阅:</strong>--%>
                                            <%--&lt;%&ndash;<%=request.getAttribute("content")%></h3>&ndash;%&gt;--%>

                                        <%--<textarea class="span12 wysihtml5 m-wrap" readonly type="text" rows="6" name="studentHWText"><%=request.getAttribute("content")%>--%>
                                            <%--</textarea>--%>
                                        <%--</h3>--%>
                                    <%--<textarea class="span10 m-wrap" rows="8"></textarea>--%>
                                            <div class="control-group">

                                                <label class="control-label" style=" font-weight:bolder">查阅</label>

                                                <div class="controls">

                                            <textarea class="span12 wysihtml5 m-wrap" readonly type="text" rows="6" name="studentHWText"><%=request.getAttribute("content")%>
                                            </textarea>

                                                </div>

                                            </div>

                                            <%--<div class="row-fluid span10">--%>
                                                <%--<a href="DownloadFile?fid=<%=request.getAttribute("fid")%>"> 附件--%>
                                                <%--</a>--%>
                                            <%--</div>--%>
                                            <div class="control-group">

                                                <label class="control-label" style=" font-weight:bolder">附件:</label>

                                                <div class="controls">

                                                    <a href="DownloadFile?fid=<%=request.getAttribute("fid")%>">
                                                        <label class="control-label" style=" font-weight:bolder">
                                                            <%=request.getAttribute("fname")%>
                                                        </label></a>

                                                </div>

                                            </div>

                                    </div>

                                    <br/>

                                    <hr/>

                                    <div class="post-comment">

                                        <h3><strong>批示</strong></h3>

                                        <form action="TeacherHomeworkChecking?hid=<%=request.getAttribute("hid")%>&sid=<%=request.getAttribute("sid")%>" method="post"

                                               class="form-horizontal">

                                            <label>

                                                <big>分数</big><span class="color-red">*</span>

                                                <input type="text" class="span1" name="score" value="<%=request.getAttribute("score")%>">

                                                <strong><big><big>/100</big></big></strong>

                                            </label>


                                            <label><big>作业评价</big></label>

                                            <textarea class="span10 m-wrap" rows="8" name="_value"><%=request.getAttribute("remark")%></textarea>

                                            <div class="form-actions">
                                                <button type="submit" class="btn purple">提交</button>

                                                <a href="TeacherHomeworkCheck?hid=<%=request.getAttribute("hid")%>">
                                                     <button type="button" class="btn">取消</button>
                                                </a>
                                            </div>

                                        </form>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </div>


            </div>

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

<script src="media/js/ckeditor/ckeditor.js"></script>

<script> CKEDITOR.replace('studentHWText');</script>

<script>

    jQuery(document).ready(function() {

        App.init();
        FormValidation.init();
        UIJQueryUI.init();
    });

</script>

<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>