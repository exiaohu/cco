<%@ page import="com.buaa.mooc.dao.StudentCourseDao" %>
<%@ page import="com.buaa.mooc.entity.Course" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: huxia
  Date: 2017/6/28
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<!-- BEGIN SIDEBAR -->

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div class="page-sidebar nav-collapse collapse">

    <!-- BEGIN SIDEBAR MENU -->

    <ul class="page-sidebar-menu">


        <div class=" sidebar-content">

            <div class="icon_head">

                <br/>

                <h3 align="center"
                    style="color:white; font-family:'汉仪粗圆简'"><%=request.getSession().getAttribute("sname")%>
                </h3>

                <br/>

                <p align="center" style="color:white; font-family:'幼圆'">软件学院</p>

            </div>

        </div>


        <li class="start active ">

            <a href="student">

                <i class="icon-home"></i>

                <span class="title">主页</span>

                <span class="selected"></span>

            </a>

        </li>


        <li class=" ">

            <a href="#">

                <i class="icon-bookmark-empty"></i>

                <span class="title">课程管理</span>


                <span class="selected"></span>

                <span class="arrow open"></span>

            </a>

            <ul class="sub-menu">

                <%
                    List<Course> courses = new StudentCourseDao().getCourses((Integer) request.getSession().getAttribute("sid"));

                    if (courses != null && courses.size() > 0) {
                        for (Course course : courses) {
                %>
                <li class="active">

                    <a href="StudentCourse?cid=<%=course.getCid()%>"><%=course.getCname()%>
                    </a>

                </li>
                <%
                        }
                    }
                %>

            </ul>

        </li>
        <!--
        <li class=" ">

            <a href="#">

                <i class="icon-file-text"></i>

                <span class="title">团队管理</span>


                <span class="selected"></span>

                <span class="arrow open"></span>

            </a>

            <ul class="sub-menu">

                <li class="active">

                    <a href="student_team_apply.html">团队申报</a>

                </li>

                <li >

                    <a href="student_team_join.html">加入团队</a>

                </li>

                <li >

                    <a href="student_team_manage.html">团队信息</a>

                </li>

            </ul>

        </li>
-->


    </ul>

    <!-- END SIDEBAR MENU -->

</div>

<!-- END SIDEBAR -->
