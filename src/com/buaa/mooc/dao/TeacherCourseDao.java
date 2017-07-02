package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Course;
import com.buaa.mooc.entity.Teacher;
import com.buaa.mooc.entity.TeacherCourse;
import com.buaa.mooc.entity.TeacherCoursePK;
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
        try {
            courses = new ArrayList<>();
            String hql = "select c from Course as c, TeacherCourse as tc where tc.pk.cid = c.cid and tc.pk.tid = :tid";
            Query query = session.createQuery(hql);
            query.setParameter("tid", tid);
            results = query.list();
            if (results != null && results.size() > 0) {
                Course c;
                for (Object obj : results) {
                    c = (Course) obj;
                    courses.add(c);
                }
            }
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public List<Teacher> findByCid(Integer cid) {
        Session session = HibernateUtils.getSession();
        List results = null;
        List<Teacher> teachers = null;
        try {
            teachers = new ArrayList<>();
            String hql = "select t from Teacher as t, TeacherCourse as tc where tc.pk.tid = t.tid and tc.pk.cid = :cid";
            Query query = session.createQuery(hql);
            query.setParameter("cid", cid);
            results = query.list();
            if (results != null && results.size() > 0) {
                Teacher t;
                for (Object obj : results) {
                    t = (Teacher) obj;
                    teachers.add(t);
                }
            }
            return teachers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public List<Integer> findTidByCid(Integer cid) {
        Session session = HibernateUtils.getSession();
        List results = null;
        List<Integer> teachers = null;
        try {
            teachers = new ArrayList<>();
            String hql = "select t.tid from Teacher as t, TeacherCourse as tc where tc.pk.tid = t.tid and tc.pk.cid = :cid";
            Query query = session.createQuery(hql);
            query.setParameter("cid", cid);
            results = query.list();
            if (results != null && results.size() > 0) {
                Integer t;
                for (Object obj : results) {
                    t = (Integer) obj;
                    teachers.add(t);
                }
            }
            return teachers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public boolean addRelationTC(Integer cid, Integer tid) {
        Session session = HibernateUtils.getSession();
        try {
            TeacherCourse tc = new TeacherCourse();
            TeacherCoursePK tcPK = new TeacherCoursePK();
            tcPK.setCid(cid);
            tcPK.setTid(tid);
            tc.setPk(tcPK);
            try {
                session.load(TeacherCourse.class, tcPK);
            } catch (Throwable e) {
                session.beginTransaction();
                session.save(tc);
                session.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public boolean deleteRelationTC(Integer cid, Integer tid) {
        Session session = HibernateUtils.getSession();
        try {
            TeacherCoursePK tcPK = new TeacherCoursePK();
            tcPK.setCid(cid);
            tcPK.setTid(tid);
            session.beginTransaction();
            TeacherCourse tc = null;
            try {
                tc = session.load(TeacherCourse.class, tcPK);
            } catch (Throwable ignored) {

            }
            if (tc != null) {
                session.delete(tc);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
