package com.buaa.mooc.dao;

import com.buaa.mooc.entity.GroupRecruit;
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
public class GroupRecruitDao {
    public Map<GroupRecruit, Long> findByCidwithMemCount(Integer cid) {
        Session session = HibernateUtils.getSession();
        List results = new ArrayList<>();
        Map <GroupRecruit, Long> groupRecruitswithMemCount = new HashMap<>();
        try {
            String hql = "select g, count(*) " +
                    "from GroupRecruit as g, StudentJoinGroup as sg " +
                    "where g.grid = sg.pk.grid and sg.granted = 1 and  g.cid = :cid " +
                    "group by g.grid";
            Query query = session.createQuery(hql);
            query.setParameter("cid", cid);
            results = query.list();
            if (results != null && results.size() > 0) {
                for (Object obj : results) {
                    Object[] item = (Object[]) obj;
                    groupRecruitswithMemCount.put((GroupRecruit)item[0], (Long) item[1]);
                }
            }
            return groupRecruitswithMemCount;
        } catch (Exception e) {
            e.printStackTrace();
            return groupRecruitswithMemCount;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
