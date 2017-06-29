<%--
  Created by IntelliJ IDEA.
  User: huxia
  Date: 2017/6/28
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<<!-- BEGIN SIDEBAR -->


<div class="page-sidebar nav-collapse collapse">

    <!-- BEGIN SIDEBAR MENU -->

    <ul class="page-sidebar-menu">
        <li class="dropdown user">

            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <span class="username">欢迎您，<%=request.getSession().getAttribute("tname")%></span>

                <i class="icon-angle-down"></i>

            </a>

            <ul class="dropdown-menu">

                <li><a href="login.html"><i class="icon-key"></i>登出</a></li>

            </ul>

        </li>

        <li class=" ">

            <a href="teacher">

                <i class="icon-home"></i>

                <span class="title">主页</span>

            </a>

        </li>

        <!--
        <li class="">

            <a href="teacher_semester.html">

                <i class="icon-table"></i>

                <span class="title">学期列表</span>

            </a>
        </li>
        -->

        <li class="">

            <a href="teacherCourse">

                <i class="icon-file-text"></i>

                <span class="title">课程管理</span>

            </a>
        </li>

        <li class="">

            <a href="TeacherHomework">

                <i class="icon-file-text"></i>

                <span class="title">作业管理</span>

            </a>


        </li>
        <!--

        <li class="last ">

            <a href="teacher_resources.html">

                <i class="icon-folder-open"></i>

                <span class="title">资源管理</span>

            </a>

        </li>
        -->
    </ul>

    <!-- END SIDEBAR MENU -->

</div>

<!-- END SIDEBAR -->
