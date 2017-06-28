package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Course;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;

import java.sql.Date;

public class CourseDao {

	//�������ӿγ�
	public void addCourse(String cname, Integer credit, Date beginDate, Date endDate, String address, Integer termId){
		Session session = HibernateUtils.getSession();
		Course course = new Course();
		course.setCname(cname);
		course.setCredit(credit);
		course.setBeginDate(beginDate);
		course.setEndDate(endDate);
		course.setAddress(address);
		course.setTermId(termId);
		try{
			session.beginTransaction();
			session.save(course);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			HibernateUtils.closeSession(session);
		}
	}
	
	//��ʦ�༭�γ̴��
	public void editCourse(Integer cid,String outline, String filename){
		Session session = null;
		try{
			session = HibernateUtils.getSession();
			Course course ;
			session.beginTransaction();
			course = (Course) session.get(Course.class, cid);
			course.setOutline(outline);
			course.setAccessory(filename);
			session.update(course);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			HibernateUtils.closeSession(session);
		}
	}

	public Course findByCid(Integer cid) {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			return session.load(Course.class, cid);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
}
