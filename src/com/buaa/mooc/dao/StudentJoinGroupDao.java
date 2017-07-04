package com.buaa.mooc.dao;

import com.buaa.mooc.entity.GroupRecruit;
import com.buaa.mooc.entity.StudentJoinGroup;
import com.buaa.mooc.entity.StudentJoinGroupPK;
import com.buaa.mooc.entity.Student;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huxia on 2017/7/2.
 */
public class StudentJoinGroupDao {
    public boolean AddRelationSGR(Integer sid, Integer grid, Integer granted) {
        Session session = HibernateUtils.getSession();
        try {
            StudentJoinGroup sg = new StudentJoinGroup();
            StudentJoinGroupPK pk = new StudentJoinGroupPK();
            pk.setSid(sid);
            pk.setGrid(grid);
            sg.setPk(pk);
            try {
                session.load(StudentJoinGroup.class, pk);
            } catch (Throwable e) {
                session.beginTransaction();
                sg.setGranted(granted);
                session.save(sg);
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

    public boolean AgreeJoin(int grid, int sid) {
        Session session = HibernateUtils.getSession();
        try {
            StudentJoinGroup sg;
            StudentJoinGroupPK pk = new StudentJoinGroupPK();
            pk.setSid(sid);
            pk.setGrid(grid);
                sg = session.load(StudentJoinGroup.class, pk);
                sg.setGranted(1);
            session.beginTransaction();
                session.update(sg);
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

    public boolean DisAgreeJoin(int grid, int sid) {
        Session session = HibernateUtils.getSession();
        try {
            StudentJoinGroup sg;
            StudentJoinGroupPK pk = new StudentJoinGroupPK();
            pk.setSid(sid);
            pk.setGrid(grid);
            sg = session.load(StudentJoinGroup.class, pk);
            session.beginTransaction();
            session.delete(sg);
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

    public List<Student> findByGridwithMemName(Integer grid) {
        Session session = HibernateUtils.getSession();
        try {
            String hql = "select s " +
                    "from Student as s, StudentJoinGroup as sg " +
                    "where s.sid = sg.pk.sid and sg.granted = 1 and sg.pk.grid = :grid";
            Query query = session.createQuery(hql);
            query.setParameter("grid", grid);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
