<%--
  Created by IntelliJ IDEA.
  User: 我不承认
  Date: 2017/7/4
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" language="java" import="com.buaa.mooc.entity.GroupRecruitView" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>

<html>
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

    <script language="JavaScript">
        function showRefuseModel(grid) {
            $("#form_modal2").attr("style", "margin-top: -105px; display: none;");
            $("#refuseForm").attr("action", "TeacherRefuseGroup?grid=" + grid).attr("method", "post");
        }
    </script>

</head>
<body class="page-header-fixed">

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

                            <a href="">团队管理</a>

                        </li>

                    </ul>


                    <!-- END PAGE TITLE & BREADCRUMB-->


                    <!-- BEGIN EXAMPLE TABLE PORTLET-->

                    <div class="portlet box purple">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-edit"></i>申请列表</div>

                        </div>

                        <div class="portlet-body">

                            <div style="padding:15px"></div>

                            <table class="table table-striped table-hover table-bordered" id="sample_editable_1">

                                <thead>

                                <tr>

                                    <th>团队名</th>

                                    <th>组长</th>

                                    <th>团队人数</th>

                                    <th>审核</th>

                                    <th>团队信息</th>

                                    <th>备注</th>

                                </tr>

                                </thead>

                                <tbody>
                                <%
                                    Map<GroupRecruitView, String> map = (Map<GroupRecruitView, String>) request.getAttribute("groupRecruitInfo");
                                    if (!(map != null && map.size() > 0)) {
                                %>
                                <tr>
                                    <td align="center"><a>没有团队申请</a></td>
                                </tr>
                                <%
                                } else {
                                    for (GroupRecruitView key : map.keySet()) {
                                %>
                                <tr align="center">

                                    <td align="center"><%=key.getGroup_name() %>
                                    </td>
                                    <td align="center"><%=map.get(key) %>
                                    </td>
                                    <td align="center"><%=key.getS_count() %>
                                    </td>
                                    <td align="center">
                                        <a href="TeacherAgreeGroup?grid=<%=key.getGrid()%>">同意</a>
                                        <a onclick="showRefuseModel(<%=key.getGrid()%>)">拒绝</a>
                                    </td>
                                    <td align="center"><a href="TeacherGroupInfo?grid=<%=key.getGrid()%>">查看团队信息</a>
                                    </td>
                                    <td align="center"><%=key.getRecruit_information()%>
                                    </td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                                </tbody>

                            </table>

                            <div id="form_modal2" class="modal hide fade" tabindex="-1" role="dialog"
                                 aria-labelledby="myModalLabel2" aria-hidden="true">

                                <div class="modal-header">

                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true"></button>

                                    <h3 id="myModalLabel2">理由</h3>

                                </div>

                                <div class="modal-body">

                                    <form id="refuseForm" action="" class="form-horizontal">

                                        <div class="control-group">

                                            <div class="controls">

                                                <input name="reason" type="text" class="span2 m-wrap"/>

                                            </div>

                                        </div>

                                        <div class="modal-footer">

                                            <input type="submit" class="btn green btn-primary" data-dismiss="modal"
                                                   value="提交"/>

                                            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                        </div>

                                    </form>

                                </div>

                            </div>

                        </div>


                    </div>

                </div>

                <!-- END SAMPLE TABLE PORTLET-->
            </div>

        </div>

    </div>

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

        App.init();
    })
</script>

<!-- END JAVASCRIPTS -->

</body>
</html>
