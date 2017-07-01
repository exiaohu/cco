package com.buaa.mooc.dao;


import com.buaa.mooc.entity.Course;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao {

    public List<Course> getCourses(Integer sid) {

        Session session = HibernateUtils.getSession();
        List<Course> courses = new ArrayList<>();
        try {
            String hql = "select c from Course as c, StudentCourse as sc where c.cid = sc.pk.cid and sc.pk.sid = :sid";
            Query query = session.createQuery(hql);
            query.setParameter("sid", sid);
            courses = query.list();
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return courses;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
