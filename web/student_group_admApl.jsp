<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<% Integer cid = (Integer) request.getAttribute("cid"); %>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8"/>

    <title>召集团队</title>

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

<!-- BEGIN HEADER -->

<jsp:include page="student_header.jsp"/>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container row-fluid">

    <!-- BEGIN SIDEBAR -->

    <jsp:include page="student_sidebar.jsp"/>

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

                    <h3 class="page-title">组建团队</h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="student">主页</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li>

                            <a href="StudentGroupHome?cid=<%=cid%>">团队管理</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li><a href="StudentGroupAdmApl?cid=<%=cid%>">组建团队</a></li>

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

                                <span class="hidden-480">申请信息</span>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <div class="tabbable portlet-tabs">

                                <br><br><br>

                                <div class="tab-content">

                                    <div class="tab-pane active" id="portlet_tab1">

                                        <!-- BEGIN FORM-->

                                        <form action="StudentGroupAdmApl" method="post" class="form-horizontal">

                                            <input type="hidden" name="cid" value="<%=request.getAttribute("cid")%>"/>

                                            <div class="control-group">

                                                <label class="control-label">小组名称：</label>

                                                <div class="controls">

                                                    <input name="group_name" type="text" placeholder="" class="m-wrap large" value=""/>

                                                    <span class="help-inline"></span>

                                                </div>

                                            </div>

                                            <div class="control-group">

                                                <label class="control-label">招募信息：</label>

                                                <div class="controls">

                                                    <textarea name="recruit_information" class="m-wrap" rows="10" cols="100"
                                                              style="width:80%"></textarea>

                                                    <span class="help-inline"></span>

                                                </div>

                                            </div>

                                            <!-- END SAMPLE FORM PORTLET-->

                                            <div class="form-actions">

                                                <button type="submit" class="btn blue"><i class="icon-ok"></i>申请
                                                </button>

                                                <button type="button" class="btn">取消</button>

                                            </div>

                                        </form>

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

            <jsp:include page="footer.jsp"/>

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