package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Group;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;

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
}
