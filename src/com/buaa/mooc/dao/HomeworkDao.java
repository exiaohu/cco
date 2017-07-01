package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Homework;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxia on 2017/6/29.
 */
public class HomeworkDao {
    public List<Homework> findByTid(Integer tid) {
        Session session = HibernateUtils.getSession();
        List results = null;
        List<Homework> homeworks = null;
        try{
            homeworks = new ArrayList<>();
            String hql="select h from Homework as h, Course as c, TeacherCourse as tc where h.courseId = c.cid and c.cid = tc.pk.cid and tc.pk.tid = :tid";
            Query query = session.createQuery(hql);
            query.setParameter("tid", tid);
            results = query.list();
            if (results != null && results.size()>0) {
                Homework c;
                for (Object obj : results) {
                    c = (Homework) obj;
                    homeworks.add(c);
                }
            }
            return homeworks;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            HibernateUtils.closeSession(session);
        }
    }

    public void AddHomework(Integer cid, String hname, Timestamp startDate, Timestamp deadline, Integer _limit) {
        Session session = HibernateUtils.getSession();
        try{
            session.beginTransaction();
            Homework homework = new Homework();
            homework.setCourseId(cid);
            homework.setHomeworkName(hname);
            homework.setStartTime(startDate);
            homework.setDeadLine(deadline);
            homework.setSubmitMaxTimes(_limit);
            session.save(homework);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally{
            HibernateUtils.closeSession(session);
        }
    }
    public void EditHomework(Integer hid, String imformation) {
        Session session = HibernateUtils.getSession();
        try{
            session.beginTransaction();
            Homework homework = findByHid(hid);
            homework.setHomeworkInformation(imformation);
            session.update(homework);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally{
            HibernateUtils.closeSession(session);
        }
    }

    public void delHomework(Integer hid){
        Session session = HibernateUtils.getSession();
        try{
            session.beginTransaction();
            Homework homework=new Homework();
            homework.setHomeworkId(hid);
            session.delete(homework);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally{
            HibernateUtils.closeSession(session);
        }
    }

    public Homework findByHid(Integer hid) {
        Session session = HibernateUtils.getSession();
        try{
            return session.load(Homework.class,hid);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            HibernateUtils.closeSession(session);

        }
    }
}
