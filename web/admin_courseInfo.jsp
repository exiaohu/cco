<%@ page import="com.buaa.mooc.entity.Course" %>
<%@ page import="com.buaa.mooc.entity.Student" %>
<%@ page import="com.buaa.mooc.entity.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8"/>

    <title>课程信息</title>

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

    <script language="JavaScript">
        function confirmSid() {
            var StudentIds = $("#StudentIds").val();
            $.ajax({
                url: "AddStudentInfo",
                type: "POST",
                data: {
                    'StudentIds': StudentIds
                },
                dataType: "json",
                success: function (data) {
                    alert("ssssssss" + data);
                    $.each(data, function (val) {
                        var extra = "";
                        extra += '<tr><td>'
                            + val.sid + '</td><td>'
                            + val.sname + '</td><td>'
                            + val.sex + '</td><td>'
                            + val.className + '</td><td>'
                            + val.major + '</td></tr>';
                    });
                    $('#table').find('tbody').append(extra);
                    alert("ssssssssssssss");
                },
                error: function (err) {
                    alert("error" + err);
                }
            });
        }
    </script>

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<jsp:include page="admin_header.jsp"/>

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

        <% Course course = (Course) request.getAttribute("course"); %>

        <% List<Student> students = (List<Student>) request.getAttribute("students"); %>

        <% List<Teacher> teachersFalse = (List<Teacher>) request.getAttribute("teachersFalse"); %>

        <% List<Teacher> teachersTrue = (List<Teacher>) request.getAttribute("teachersTrue"); %>

        <div class="container-fluid">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                        课程信息管理
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

                        <li><a href="EduAdminCourseInfo?<%=course.getCid()%>">课程信息</a></li>

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

                                <span class="hidden-480">课程信息</span>

                            </div>

                        </div>

                        <div class="portlet-body form">

                            <div class="tabbable portlet-tabs">

                                <ul class="nav nav-tabs">


                                    <li class="active"><a href="#portlet_tab1" data-toggle="tab">查看</a></li>
                                </ul>

                                <div class="tab-content">

                                    <div class="tab-pane active" id="portlet_tab1">

                                        <!-- BEGIN FORM-->

                                        <form action="EduAdminCourseInfo" method="post" class="form-horizontal">

                                            <input type="hidden" name="cid" value="<%=course.getCid()%>">

                                            <div class="control-group">

                                                <label class="control-label">课程名：</label>

                                                <div class="controls">

                                                    <input name="cname" type="text" placeholder="" class="m-wrap large"
                                                           value="<%=course.getCname()%>"/>

                                                    <span class="help-inline"></span>

                                                </div>

                                            </div>

                                            <div class="control-group">

                                                <label class="control-label">起始时间：</label>

                                                <div class="controls">

                                                    <input name="beginDate" type="text" placeholder=""
                                                           class="m-wrap small"
                                                           value="<%=course.getBeginDate().toLocalDate().getMonthValue()%>/<%=course.getBeginDate().toLocalDate().getDayOfMonth()%>/<%=course.getBeginDate().toLocalDate().getYear()%>"/>

                                                    <span class="help-inline"></span>

                                                </div>
                                                <div class="control-group">

                                                    <label class="control-label">结束时间：</label>

                                                    <div class="controls">

                                                        <input name="endDate" type="date" placeholder=""
                                                               class="m-wrap small"
                                                               value="<%=course.getEndDate().toLocalDate().getMonthValue()%>/<%=course.getEndDate().toLocalDate().getDayOfMonth()%>/<%=course.getEndDate().toLocalDate().getYear()%>"/>

                                                        <span class="help-inline"></span>

                                                    </div>

                                                </div>
                                                <div class="control-group">

                                                    <label class="control-label">学分：</label>

                                                    <div class="controls">

                                                        <input name="credit" type="date" placeholder=""
                                                               class="m-wrap small" value="<%=course.getCredit()%>"/>

                                                        <span class="help-inline"></span>

                                                    </div>

                                                </div>

                                                <div class="control-group">

                                                    <label class="control-label">任课老师：</label>

                                                    <div class="controls">

                                                        <%
                                                            if (teachersFalse != null && teachersFalse.size() > 0) {
                                                                for (Teacher teacher : teachersFalse) {
                                                        %>

                                                        <label class="control-label">
                                                            <input name="teacher" type="checkbox" class="m-wrap small"
                                                                   value="<%=teacher.getTid()%>"/><%=teacher.getTname()%>
                                                        </label>

                                                        <%
                                                                }
                                                            }
                                                        %>

                                                        <%
                                                            if (teachersTrue != null && teachersTrue.size() > 0) {
                                                                for (Teacher teacher : teachersTrue) {
                                                        %>

                                                        <label class="control-label">
                                                            <input name="teacher" type="checkbox" class="m-wrap small"
                                                                   value="<%=teacher.getTid()%>"
                                                                   checked="checked"/><%=teacher.getTname()%>
                                                        </label>

                                                        <%
                                                                }
                                                            }
                                                        %>

                                                    </div>

                                                </div>
                                                <div class="control-group">

                                                    <label class="control-label">上课地点：</label>

                                                    <div class="controls">

                                                        <input name="address" type="text" placeholder=""
                                                               class="m-wrap small" value="<%=course.getAddress()%>"/>

                                                        <span class="help-inline"></span>

                                                    </div>

                                                </div>

                                                <div class="portlet box blue">
                                                    <div class="portlet-title">
                                                        <div class="caption"><i class="icon-cogs"></i>参与学生学号</div>
                                                        <div class="actions">
                                                            <button onclick="confirmSid()" type="button"
                                                                    class="btn green">
                                                                <i class="icon-plus"></i>确认导入学生学号
                                                            </button>
                                                        </div>
                                                    </div>
                                                    <div class="portlet-body">
                                                        <div class="control-group">
                                                            <label class="control-label">参与学生学号：</label>
                                                            <div class="controls">
                                                                <textarea id="StudentIds" class="m-wrap" rows="10"
                                                                          cols="100"
                                                                          style="width:80%"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>


                                            <div class="portlet box blue">

                                                <div class="portlet-title">

                                                    <div class="caption"><i class="icon-cogs"></i>学生信息</div>

                                                    <div class="tools">

                                                        <a href="javascript:" class="collapse"></a>

                                                        <a href="#portlet-config" data-toggle="modal"
                                                           class="config"></a>

                                                        <a href="javascript:" class="reload"></a>

                                                        <a href="javascript:" class=""></a>

                                                    </div>

                                                </div>

                                                <div class="portlet-body flip-scroll">

                                                    <table class="table-bordered table-striped table-condensed flip-content">

                                                        <thead class="flip-content">

                                                        <tr>

                                                            <th class="numeric">学号</th>

                                                            <th>姓名</th>

                                                            <th class="numeric">性别</th>

                                                            <th class="numeric">班级</th>

                                                            <th class="numeric">专业</th>

                                                        </tr>

                                                        </thead>

                                                        <tbody>

                                                        <%
                                                            if (students != null && students.size() > 0) {
                                                                for (Student student : students) {
                                                        %>

                                                        <tr>

                                                            <td><%=student.getSid()%>
                                                            </td>

                                                            <td><%=student.getSname()%>
                                                            </td>

                                                            <td class="numeric"><%=(Objects.equals(student.getSex(), "f") ? "女" : "男")%>
                                                            </td>

                                                            <td class="numeric"><%=student.getClassName()%>
                                                            </td>

                                                            <td class="numeric"><%=student.getMajor()%>
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

                                        </form>

                                    </div>

                                    <!-- END SAMPLE FORM PORTLET-->
                                    <div class="form-actions">

                                        <button type="submit" class="btn blue"><i class="icon-ok"></i>Save</button>

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

    </div>

</div>

</body>

<!-- END BODY -->