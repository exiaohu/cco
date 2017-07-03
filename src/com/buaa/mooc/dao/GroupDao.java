package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Group;
import com.buaa.mooc.entity.Student;
import com.buaa.mooc.entity.StudentCourse;
import com.buaa.mooc.entity.StudentCoursePK;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huxia on 2017/7/2.
 */
public class GroupDao {
    public Group findById(Integer gid) {
        Session session = HibernateUtils.getSession();
        try {
            return session.load(Group.class, gid);
        } catch (Exception e) {
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public Map<Student, Double> findStudentByGid(Integer gid) {
        Session session = HibernateUtils.getSession();
        Map<Student, Double> studentWithGroupContribute = new HashMap<>();
        try {
            String hql = "select s, sc.group_contribute from StudentCourse as sc, Student as s where sc.pk.sid = s.sid and sc.gid = :gid";
            Query query = session.createQuery(hql);
            query.setParameter("gid", gid);
            List result = query.list();
            if (result != null && result.size() > 0) {
                for (Object obj : result) {
                    Object[] item = (Object[]) obj;
                    studentWithGroupContribute.put((Student) item[0], (Double) item[1]);
                }
            }
            return studentWithGroupContribute;
        } catch (Exception e) {
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public boolean UpdateStudentContribute(Group group, Map<Student, Double> students) {
        Session session = HibernateUtils.getSession();
        try {
            session.beginTransaction();
            for (Student student : students.keySet()) {
                StudentCoursePK scpk = new StudentCoursePK();
                scpk.setSid(student.getSid());
                scpk.setCid(group.getCid());
                StudentCourse sc = session.load(StudentCourse.class, scpk);
                sc.setGid(group.getGid());
                sc.setGroup_contribute(students.get(student));
                session.update(sc);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
