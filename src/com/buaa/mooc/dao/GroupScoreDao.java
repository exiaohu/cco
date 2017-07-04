package com.buaa.mooc.dao;

import com.buaa.mooc.entity.GroupScore;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huxia on 2017/7/4.
 */
public class GroupScoreDao {
    public Map<Integer, Double> findByCid(Integer cid) {
        Session session = HibernateUtils.getSession();
        try {
            Map<Integer, Double> retVal = new HashMap<>();
            String hql = "select gs from GroupScore as gs, Group as g where g.gid = gs.gid and g.cid = :cid";
            Query query = session.createQuery(hql);
            query.setParameter("cid", cid);
            List result = query.list();
            for (Object obj : result) {
                GroupScore gs = (GroupScore) obj;
                retVal.put(gs.getGid(), gs.getGroupScore());
            }
            return retVal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public Double findByGid(Integer gid) {
        Session session = HibernateUtils.getSession();
        try {
            String hql = "select gs.groupScore from GroupScore as gs where gs.gid = :gid";
            Query query = session.createQuery(hql);
            query.setParameter("gid", gid);
            return (Double) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public static void main(String[] args) {
        GroupScoreDao groupScoreDao = new GroupScoreDao();
        System.out.println(groupScoreDao.findByGid(1));
    }
}
