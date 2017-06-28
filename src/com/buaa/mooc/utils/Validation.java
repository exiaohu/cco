package com.buaa.mooc.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huxia on 2017/6/28.
 */
public class Validation {

    static public boolean checkStudent(HttpServletRequest request) {
        return (request.getSession().getAttribute("sid") != null && request.getSession().getAttribute("sid") instanceof Integer);
    }

    static public boolean checkTeacher(HttpServletRequest request) {
        return (request.getSession().getAttribute("tid") != null && request.getSession().getAttribute("tid") instanceof Integer);
    }

    static public boolean checkEduAdmin(HttpServletRequest request) {
        return (request.getSession().getAttribute("eid") != null && request.getSession().getAttribute("eid") instanceof Integer);
    }
}
