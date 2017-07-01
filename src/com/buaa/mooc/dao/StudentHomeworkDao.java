package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Homework;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windrises on 2017/6/28.
 */
public class StudentHomeworkDao {
    public List<Homework> getHomeworkByCid(Integer cid){

        Session session = HibernateUtils.getSession();
        List<Homework> homeworks = new ArrayList<>();
        try{
            String hql="select h from Homework as h where h.courseId = :cid";
            Query query=session.createQuery(hql);
            query.setParameter("cid", cid);
            homeworks = query.list();
            return homeworks;
        }catch(Exception e){
            e.printStackTrace();
            return homeworks;
        }finally{
            HibernateUtils.closeSession(session);
        }
    }

    //偷懒，老师和学生都展示所有的课程的所有作业
    public List<Homework> getAllHomeworks(){

        Session session = HibernateUtils.getSession();
        List<Homework> homeworks = null;
        try{
            String hql="from Homework";
            Query query=session.createQuery(hql);
            homeworks = query.list();
            return homeworks;
        }catch(Exception e){
            e.printStackTrace();
            return homeworks;
        }finally{
            HibernateUtils.closeSession(session);
        }
    }

    public static void main(String args[]){
        StudentHomeworkDao hd = new StudentHomeworkDao();
        List<Homework> homeworkds = hd.getAllHomeworks();
        for(Homework homework : homeworkds){
            System.out.println(homework.getHomeworkName());
        }
    }
}
