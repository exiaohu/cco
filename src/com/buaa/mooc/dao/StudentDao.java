package com.buaa.mooc.dao;

import org.hibernate.Session;

import com.buaa.mooc.entity.Student;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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

    public List<Student> findByCourseId(Integer cid) {
        Session session = HibernateUtils.getSession();
        List results = null;
        List<Student> students = null;
        try{
            students = new ArrayList<>();
            String hql="select s from Student as s, StudentCourse as sc where s.sid =  sc.pk.sid and sc.pk.cid = :cid";
            Query query = session.createQuery(hql);
            query.setParameter("cid", cid);
            results = query.list();
            if (results != null && results.size()>0) {
                Student s;
                for (Object obj : results) {
                    s = (Student) obj;
                    students.add(s);
                }
            }
            return students;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            HibernateUtils.closeSession(session);
        }
    }
}
