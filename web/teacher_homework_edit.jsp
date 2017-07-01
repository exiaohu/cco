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

    <link rel="stylesheet" type="text/css" href="media/css/select2_metro.css"/>

    <link rel="stylesheet" type="text/css" href="media/css/chosen.css"/>

    <link rel="stylesheet" type="text/css" href="media/css/jquery-ui-1.10.1.custom.min.css"/>

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="media/image/favicon.ico"/>

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<!-- BEGIN HEADER -->

<jsp:include page="teacher_header.jsp"/>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container row-fluid">

    <jsp:include page="teacher_sidebar.jsp"/>
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

                        编辑作业

                        <small>编辑当前作业详细信息</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="teacher">主页</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li>

                            <a href="TeacherHomework">作业管理</a>

                            <span class="icon-angle-right"></span>

                        </li>

                        <li><a href="TeacherHomeworkEdit?hid=<%=request.getAttribute("hid")%>">编辑作业</a></li>

                    </ul>

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->


            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN VALIDATION STATES-->

                    <div class="portlet box purple">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-edit"></i>作业详情</div>

                            <div class="tools">

                                <a href="javascript:" class="collapse"></a>

                                <a href="#portlet-config" data-toggle="modal" class="config"></a>

                                <a href="javascript:" class="reload"></a>

                                <a href="javascript:" class="remove"></a>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <!-- BEGIN FORM-->

                            <form action="TeacherHomeworkEdit?hid=<%=request.getAttribute("hid")%>" method="post"

                                  id="form_sample_1" class="form-horizontal">

                                <div class="alert alert-error hide">

                                    <button class="close" data-dismiss="alert"></button>

                                    You have some form errors. Please check below.

                                </div>

                                <div class="alert alert-success hide">

                                    <button class="close" data-dismiss="alert"></button>

                                    Your form validation is successful!

                                </div>

                                <input name="id" type="hidden" value="<%=request.getAttribute("hid")%>"/>

                                <div class="control-group">

                                    <label class="control-label">作业名称：<span class="required">*</span></label>

                                    <div class="controls">

                                        <!--<input value="软件开发实习" name="assign_name" type="text" class="span6 m-wrap"/>*/-->
                                        <%=request.getAttribute("hname")%>
                                    </div>

                                </div>

                                <div class="control-group">

                                    <label class="control-label">起止日期<span class="required">*</span></label>

                                    <div class="controls">

                                        <%=request.getAttribute("startDate")%>
                                        &nbsp;&nbsp;至
                                        <%=request.getAttribute("deadLine")%>
                                    </div>

                                </div>

                                <div class="control-group">

                                    <label class="control-label">详细需求<span class="required">*</span></label>

                                    <div class="controls">

                                        <textarea class="span6 m-wrap" rows="3" name="request">
                                            <%=request.getAttribute("imformation") != null ? request.getAttribute("imformation") : ""%>
                                        </textarea>

                                    </div>

                                </div>

                                <div class="form-actions">

                                    <button type="submit" class="btn purple">更改</button>

                                    <a href="TeacherHomework">
                                        <button type="button" class="btn">取消</button>
                                    </a>

                                </div>

                            </form>

                            <!-- END FORM-->

                        </div>

                    </div>

                    <!-- END VALIDATION STATES-->

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

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script type="text/javascript" src="media/js/jquery.validate.min.js"></script>

<script type="text/javascript" src="media/js/additional-methods.min.js"></script>

<script type="text/javascript" src="media/js/select2.min.js"></script>

<script type="text/javascript" src="media/js/chosen.jquery.min.js"></script>

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL STYLES -->

<script src="media/js/app.js"></script>

<script src="media/js/form-validation.js"></script>

<script src="media/js/ui-jqueryui.js"></script>

<!-- END PAGE LEVEL STYLES -->

<script>

    jQuery(document).ready(function () {

        // initiate layout and plugins

        App.init();

        FormValidation.init();

        UIJQueryUI.init();

    });

</script>

<!-- END JAVASCRIPTS -->

<script type="text/javascript">  var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-37564768-1']);
_gaq.push(['_setDomainName', 'keenthemes.com']);
_gaq.push(['_setAllowLinker', true]);
_gaq.push(['_trackPageview']);
(function () {
    var ga = document.createElement('script');
    ga.type = 'text/javascript';
    ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(ga, s);
})();</script>
</body>

<!-- END BODY -->

</html>