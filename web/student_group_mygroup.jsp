<%@ page import="com.buaa.mooc.dao.CourseDao" %>
<%@ page import="com.buaa.mooc.dao.StudentDao" %>
<%@ page import="com.buaa.mooc.entity.Group" %>
<%@ page import="com.buaa.mooc.entity.Student" %>
<%@ page import="java.util.Map" %>
<% Group group = (Group) request.getAttribute("group"); %>
<% Map<Student, Double> students = (Map<Student, Double>) request.getAttribute("students"); %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8" />

    <title>我的团队</title>

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

    <link rel="stylesheet" type="text/css" href="media/css/select2_metro.css" />

    <link rel="stylesheet" href="media/css/DT_bootstrap.css" />

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="media/image/favicon.ico" />

    <script language="JavaScript">
        function updateStudentContribute() {
            var data;
            data.gid = <%=group.getGid()%>;
            <%
            if (students != null && students.size() > 0) {
                for (Student student : students.keySet()) {
            %>
            data.<%=student.getSid()%> = $("#<%=student.getSid()%>").val();
            <%
                }
            }
            %>
            $.ajax({
                url: "StudentUpdateGroupContribute",
                type: "POST",
                data: data,
                dataType: "json",
                success: function (res) {
                    alert(res);
                },
                error: function (err) {
                    alert(err);
                }
            });
        }
    </script>

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

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        我的团队

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="student">主页</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <i class=""></i>

                            <a href="StudentGroupHome?cid=<%=group.getCid()%>">团队管理</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li>

                            <i class=""></i>

                            <a href="StudentMyGroup?gid=<%=group.getGid()%>">我的团队</a>

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

                    <div class="portlet box purple">

                        <div class="portlet-title">
                            <div class="caption"><i class="icon-table"></i>小组信息</div>
                        </div>

                        <div class="portlet-body">
                            <form action="#" class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label" style=" font-weight:bolder">组名：</label>
                                    <div class="controls">
                                        <span class="text"><%=group.getGname()%></span>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" style=" font-weight:bolder">组长：</label>
                                    <div class="controls">
                                        <span class="text"><%=new StudentDao().findById(group.getManager_sid())%></span>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" style=" font-weight:bolder">人数：</label>
                                    <div class="controls">
                                        <span class="text"><%=students != null ? students.size() : 0%></span>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label" style=" font-weight:bolder">课程：</label>
                                    <div class="controls">
                                        <span class="text"><%=new CourseDao().findByCid(group.getCid()).getCname()%></span>
                                    </div>
                                </div>
                            </form>
                            <div class="portlet box purple">

                                <div class="portlet-title">

                                    <div class="caption"><i class="icon-cogs"></i>组员信息</div>

                                    <div class="actions">

                                    </div>
                                </div>

                                <div class="portlet-body flip-scroll">

                                    <table class="table-bordered table-striped table-condensed flip-content">

                                        <thead class="flip-content">

                                        <tr>

                                            <th>学号</th>

                                            <th>姓名</th>

                                            <th class="numeric">性别</th>


                                        </tr>

                                        </thead>

                                        <tbody>

                                        <%
                                            if (students != null && students.size() > 0) {

                                                for (Student student : students.keySet()) {
                                        %>

                                        <tr>

                                            <td><%=student.getSid()%>
                                            </td>

                                            <td><%=student.getSname()%>
                                            </td>

                                            <td class="numeric"><%=student.getSex().equalsIgnoreCase("f") ? "女" : "男"%>
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

                            <%
                                if (request.getSession().getAttribute("sid").equals(group.getManager_sid())) {
                            %>

                            <div class="portlet box purple">

                                <div class="portlet-title">

                                    <div class="caption"><i class="icon-cogs"></i>团队贡献度</div>

                                    <div class="actions"></div>

                                </div>

                                <div class="portlet-body flip-scroll">

                                    <table class="table-bordered table-striped table-condensed flip-content">

                                        <thead class="flip-content">

                                        <tr>

                                            <th>学号</th>

                                            <th>姓名</th>

                                            <th class="numeric">团队贡献度</th>

                                        </tr>

                                        </thead>

                                        <tbody>

                                        <%
                                            if (students != null && students.size() > 0) {

                                                for (Student student : students.keySet()) {
                                        %>

                                        <tr>

                                            <td><%=student.getSid()%>
                                            </td>

                                            <td><%=student.getSname()%>
                                            </td>

                                            <td class="numeric"><input id="<%=student.getSid()%>" name="<%=student.getSid()%>" type="number" value="<%=students.get(student)%>">
                                            </td>

                                        </tr>

                                        <%
                                                }
                                            }
                                        %>

                                        </tbody>

                                    </table>

                                    <button onclick="updateStudentContribute()" class="btn green">确认提交贡献度</button>

                                </div>

                            </div>

                            <%
                                }
                            %>

                        </div>

                        <!-- END SAMPLE TABLE PORTLET-->

                    </div>

                    <!-- END SAMPLE TABLE PORTLET-->
                </div>

            </div>

            <!-- END PAGE CONTENT-->

        </div>

    </div>

</div>

<!-- END PAGE CONTENT-->

<!-- END PAGE -->

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

<script src="media/js/jquery.uniform.min.js" type="text/javascript" ></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script type="text/javascript" src="media/js/select2.min.js"></script>

<script type="text/javascript" src="media/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="media/js/DT_bootstrap.js"></script>

<!-- END PAGE LEVEL PLUGINS -->

<script src="media/js/app.js"></script>

<script src="media/js/table-advanced.js"></script>

<script>

    jQuery(document).ready(function() {

        // initiate layout and plugins

        App.init();

        TableAdvanced.init();

    });

</script>

</body>

<!-- END BODY -->

</html>