<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
<head>

    <meta charset="utf-8"/>

    <title>主页</title>

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

    <link href="media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>


    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="media/image/favicon.ico"/>

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<jsp:include page="teacher_header.jsp"/>

<!-- BEGIN CONTAINER -->

<div class="page-container">

    <jsp:include page="teacher_sidebar.jsp"/>

    <!-- BEGIN PAGE -->

    <div class="page-content">

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        主页
                        <small>教师课程中心页面</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="teacher">主页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                    <div class="">

                        <div class="portlet-title">
                            <!--课程中心-->
                            <div class="textPanel">
                                <div class="header" style="margin: 0.0px 0.0px 11.0px;padding: 0.0px;color: rgb(0,0,0);line-height: normal;font-size: 12.0px;"> <h3 style="margin: 0.0px;padding: 0.0px;color: rgb(66,66,66);line-height: 25.0px;font-size: 18.0px;">课程中心教师使用手册</h3> </div>  <div class="context" style="margin: 0.0px;padding: 0.0px;color: rgb(0,0,0);line-height: normal;font-size: 12.0px;"> <div class="content" style="margin: 0.0px;padding: 0.0px;overflow: hidden;">
                                <p class="text-muted text-middle" style="margin: 0.0px;padding: 0.0px;color: rgb(128,128,128);line-height: 22.0px;font-size: 14.0px;">1、用户课程管理
                                    点击首页左侧课程管理按钮，跳转到课程列表，里面显示的是该名教师所教的所有课程 </p>
                                <p class="text-muted text-middle" style="margin: 0.0px;padding: 0.0px;color: rgb(128,128,128);line-height: 22.0px;font-size: 14.0px;">2、作业管理 用户点击作业管理按钮 ，会进入老师作业课程界面，在界面中，显示出的是该教室所教所以课程的列表 </p>
                                <p class="text-muted text-middle" style="margin: 0.0px;padding: 0.0px;color: rgb(128,128,128);line-height: 22.0px;font-size: 14.0px;">3、成绩管理界面 老师点击成绩管理界面，会进入成绩管理界面，在该页面中会显示老师所有的课程信息 </p>
                                <p class="text-muted text-middle" style="margin: 0.0px;padding: 0.0px;color: rgb(128,128,128);line-height: 22.0px;font-size: 14.0px;">4、团队管理 点击团地管理按钮，会进入团队管理界面，里面包括该老师所有教授的课程信息 </p>
                            </div> </div>  <p style="margin: 0.0px;padding: 0.0px;text-align: center;color: rgb(51,51,51);text-transform: none;text-indent: 25.0px;letter-spacing: normal;word-spacing: 0.0px;white-space: normal;background-color: rgb(255,255,255);"> </p>  <p style="margin: 0.0px;padding: 0.0px;text-align: center;color: rgb(51,51,51);text-transform: none;text-indent: 25.0px;letter-spacing: normal;word-spacing: 0.0px;white-space: normal;background-color: rgb(255,255,255);"><span style="color: rgb(255,0,0);"></span></p>
                                <p style="margin: 0.0px;padding: 0.0px;color: rgb(51,51,51);text-transform: none;text-indent: 25.0px;letter-spacing: normal;word-spacing: 0.0px;white-space: normal;background-color: rgb(255,255,255);"> </p>
                            </div>

                            <!-- 课程中心截得代码，比较乱-->
                        </div>

                    </div>

                </div>

            </div>

            <!-- END PAGE HEADER-->
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

<script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script src="media/js/jquery.vmap.js" type="text/javascript"></script>

<script src="media/js/jquery.vmap.russia.js" type="text/javascript"></script>

<script src="media/js/jquery.vmap.world.js" type="text/javascript"></script>

<script src="media/js/jquery.vmap.europe.js" type="text/javascript"></script>

<script src="media/js/jquery.vmap.germany.js" type="text/javascript"></script>

<script src="media/js/jquery.vmap.usa.js" type="text/javascript"></script>

<script src="media/js/jquery.vmap.sampledata.js" type="text/javascript"></script>

<script src="media/js/jquery.flot.js" type="text/javascript"></script>

<script src="media/js/jquery.flot.resize.js" type="text/javascript"></script>

<script src="media/js/jquery.pulsate.min.js" type="text/javascript"></script>

<script src="media/js/date.js" type="text/javascript"></script>

<script src="media/js/daterangepicker.js" type="text/javascript"></script>

<script src="media/js/jquery.gritter.js" type="text/javascript"></script>

<script src="media/js/fullcalendar.min.js" type="text/javascript"></script>

<script src="media/js/jquery.easy-pie-chart.js" type="text/javascript"></script>

<script src="media/js/jquery.sparkline.min.js" type="text/javascript"></script>

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="media/js/app.js" type="text/javascript"></script>

<script src="media/js/index.js" type="text/javascript"></script>

<script src="media/js/calendar.js"></script>

<!-- END PAGE LEVEL SCRIPTS -->

<script>

    jQuery(document).ready(function () {

        App.init(); // initlayout and core plugins

        Index.init();

        Index.initJQVMAP(); // init index page's custom scripts

        Index.initCharts(); // init index page's custom scripts

        Index.initMiniCharts();

        Index.initDashboardDaterange();


    });

</script>

<!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->

</html>