<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- BEGIN SIDEBAR -->
<span class="icon-dark"></span>
<div class="page-sidebar nav-collapse collapse">

    <!-- BEGIN SIDEBAR MENU -->

    <ul class="page-sidebar-menu">

        <li>
        </li>
        <li class="dropdown user">

            <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                <span class="username">您好，<%=request.getSession().getAttribute("ename")%></span>

                <i class="icon-angle-down"></i>
            </a>

            <ul class="dropdown-menu">

                <li><a href="login.html"><i class="icon-key"></i>登出</a></li>

            </ul>

        </li>
        <li class="start active ">

            <a href="eduadmin">

                <i class="icon-home"></i>

                <span class="title">主页</span>

                <span class="selected"></span>
            </a>
        </li>

        <li class="">

            <a href="semester">

                <i class="icon-table"></i>

                <span class="title">学期管理</span>

            </a>


        </li>
        <!--
        <li class="">

            <a href="admin_course.html">

                <i class="icon-cogs"></i>

                <span class="title">课程管理</span>

            </a>


        </li>-->

    </ul>

    <!-- END SIDEBAR MENU -->

</div>

<!-- END SIDEBAR -->