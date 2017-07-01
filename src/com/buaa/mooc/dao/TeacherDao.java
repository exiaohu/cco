package com.buaa.mooc.dao;

import org.hibernate.Session;
import com.buaa.mooc.utils.HibernateUtils;

import com.buaa.mooc.entity.Teacher;
import org.hibernate.query.Query;

import java.util.List;


public class TeacherDao {

	public boolean isExistByTidAndPassword(Integer tid, String password) {
		Session session = HibernateUtils.getSession();
		Teacher tea = null;
        try {
            session.beginTransaction();  
              
            tea = (Teacher)session.get(Teacher.class, tid);
            session.getTransaction().commit();
        }catch(Exception e) {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }finally {  
        	HibernateUtils.closeSession(session);
        }
        
        if(tea == null || !tea.getPassword().trim().equals(password.trim())) {
        	return false;
        } else {
        	return true;
        }
	}

    public Teacher findById(Integer tid) {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            return session.load(Teacher.class, tid) != null ? session.load(Teacher.class, tid) : null;
        }catch(Exception e) {
            return null;
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    public List<Teacher> findAll() {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            String hql = "from Teacher";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public List<Integer> findAllTid() {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            String hql = "select tid from Teacher";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

	/*
	public static void main(String[] args) {
		TeacherDao teacherDao = new TeacherDao();
		boolean flag = teacherDao.isExistByTidAndPassword(14211106, "huxiao");
		System.out.println(flag);
	}
	*/
}
