package com.buaa.mooc.dao;

import com.buaa.mooc.entity.GroupRecruit;
import com.buaa.mooc.entity.GroupRecruitView;
import com.buaa.mooc.entity.StudentRecruitView;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by huxia on 2017/7/2.
 */
public class GroupRecruitDao {
    public List<GroupRecruitView> findByCidwithMemCount(Integer cid) {
        Session session = HibernateUtils.getSession();
        List<GroupRecruitView> results;
        try {
            String hql = "select g from GroupRecruitView as g where g.cid = :cid";
            Query query = session.createQuery(hql);
            query.setParameter("cid", cid);
            results = query.list();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public StudentRecruitView findBySidandCidSRV(Integer sid, Integer cid) {
        Session session = HibernateUtils.getSession();
        List results;
        try {
            String hql = "from StudentRecruitView where cid = :cid and pk.sid = :sid";
            Query query = session.createQuery(hql);
            query.setParameter("cid", cid);
            query.setParameter("sid", sid);
            results = query.list();
            if (results != null && results.size() > 0) {
                return (StudentRecruitView)(results.get(0));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public List<StudentRecruitView> findByGridSRV(Integer grid) {
        Session session = HibernateUtils.getSession();
        try {
            String hql = "from StudentRecruitView where pk.grid = :grid";
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

    public GroupRecruit findByGridGR(Integer grid) {
        Session session = HibernateUtils.getSession();
        try {
            return session.load(GroupRecruit.class, grid);
        } catch (Exception e) {
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public static void main(String[] args) {
        GroupRecruitDao groupRecruitDao = new GroupRecruitDao();
        List<StudentRecruitView> studentRecruitViews = groupRecruitDao.findByGridSRV(1);
        for (StudentRecruitView studentRecruitView : studentRecruitViews) {
            System.out.println(studentRecruitView.getGroup_name());
        }

    }
}
