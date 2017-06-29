package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Homework;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by windrises on 2017/6/28.
 */
public class StudentHomeworkDao {
    public List<Integer> getHomeworkByCid(Integer cid){

        Session session = HibernateUtils.getSession();
        List<Homework> studentHomeworks = null;
        List<Integer> homeworks = new ArrayList<Integer>();
        try{
            String hql="from Homework";
            Query query=session.createQuery(hql);
            studentHomeworks = query.list();
            for(Homework sh: studentHomeworks){
                if (Objects.equals(sh.getCourseId(), cid)) {
                    homeworks.add(sh.getHomeworkId());
                }
            }
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
