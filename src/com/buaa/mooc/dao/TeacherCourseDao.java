package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Course;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TeacherCourseDao {
    public List<Course> findByTid(Integer tid) {
        Session session = HibernateUtils.getSession();
        List results = null;
        List<Course> courses = null;
        try{
            courses = new ArrayList<>();
            String hql="select c from Course as c, TeacherCourse as tc where tc.pk.cid = c.cid and tc.pk.tid = :tid";
            Query query=session.createQuery(hql);
            query.setParameter("tid", tid);
            results = query.list();
            if (results != null && results.size()>0) {
                Course c;
                for (Object obj : results) {
                    c = (Course) obj;
                    courses.add(c);
                }
            }
            return courses;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            HibernateUtils.closeSession(session);
        }
    }
}
