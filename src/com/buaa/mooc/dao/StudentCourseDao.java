package com.buaa.mooc.dao;


import com.buaa.mooc.entity.StudentCourse;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentCourseDao {

	public List<Integer> getCourses(Integer sid){
		
		Session session = HibernateUtils.getSession();
		List<StudentCourse> studentCourses = null;
		List<Integer> courses = new ArrayList<Integer>();
		try{
			String hql="from StudentCourse";
			Query query=session.createQuery(hql);
			studentCourses = query.list();
			for(StudentCourse sc : studentCourses){
				if (Objects.equals(sc.getPk().getSid(), sid)) {
					courses.add(sc.getPk().getCid());
				}
			}
			return courses;
		}catch(Exception e){
			e.printStackTrace();
			return courses;
		}finally{
			HibernateUtils.closeSession(session);
		}
	}
	
	public static void main(String args[]){
		StudentCourseDao scd = new StudentCourseDao();
		List<Integer> courses = scd.getCourses(1);
		for(Integer cid : courses){
			System.out.println(cid);
		}
	}
}
