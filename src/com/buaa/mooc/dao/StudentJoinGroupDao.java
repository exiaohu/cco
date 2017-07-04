package com.buaa.mooc.dao;

import com.buaa.mooc.entity.StudentJoinGroup;
import com.buaa.mooc.entity.StudentJoinGroupPK;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;

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
}
