package com.buaa.mooc.dao;

import org.hibernate.Session;

import com.buaa.mooc.entity.Student;
import com.buaa.mooc.utils.HibernateUtils;

public class StudentDao {
	public boolean isExistBySidAndPassword(Integer sid, String password) {
		Session session = HibernateUtils.getSession();
		Student stu = null;
        try {
            session.beginTransaction();  
              
            stu = (Student)session.get(Student.class, sid);
            session.getTransaction().commit();
        }catch(Exception e) {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }finally {  
        	HibernateUtils.closeSession(session);
        }

        return !(stu == null || !stu.getPassword().trim().equals(password.trim()));
	}

	public Student findById(Integer sid) {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            return session.load(Student.class, sid) != null ? session.load(Student.class, sid) : null;
        }catch(Exception e) {
            return null;
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
}
