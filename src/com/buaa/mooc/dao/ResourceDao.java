package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Course;
import com.buaa.mooc.entity.File;
import com.buaa.mooc.entity.Homework;
import com.buaa.mooc.entity.Resource;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

//import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lenovo on 2017/7/3.
 */
public class ResourceDao {
    public void delResource(Integer rid) {
        Session session = HibernateUtils.getSession();
        try {
            session.beginTransaction();
            Resource resource = new Resource();
            resource.setRid(rid);
            session.delete(resource);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public Integer AddResource(String rName, Integer id) {
        Session session = HibernateUtils.getSession();
        try {
            session.beginTransaction();
            Resource resource = new Resource();
            resource.setRname(rName);
            resource.setUploadby(id);
            session.save(resource);
            session.getTransaction().commit();

            return resource.getRid();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public void editResource(Integer cid, Integer rid, Timestamp date) {
        Session session = HibernateUtils.getSession();
        try {
            Resource resource;
            session.beginTransaction();
            resource = (Resource) session.get(Resource.class,cid);
            resource.setRid(rid);
            resource.setAttendTime(date);
            session.update(resource);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public List<Resource> FindAll() {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            String hql = "from Resource ";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public Resource getResourceByRId(Integer rid){
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            return session.load(Resource.class, rid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
