package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Homework;
import com.buaa.mooc.entity.HomeworkSubmit;
import com.buaa.mooc.entity.HomeworkSubmitPK;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by windrises on 2017/6/29.
 */
public class StudentHWSubmitDao {
    public void addHWSubmit(Integer hid, Integer sid, Integer fid, Timestamp submitTime,
                            String submitContent, Integer isCorrect, Float score,
                            String remark, Integer submitTimes){
        Session session = HibernateUtils.getSession();
        HomeworkSubmitPK homeworkSubmitPK = new HomeworkSubmitPK();
        homeworkSubmitPK.setHid(hid);
        homeworkSubmitPK.setSid(sid);
        HomeworkSubmit homeworkSubmit = new HomeworkSubmit(homeworkSubmitPK,fid,submitTime,
                 submitContent,isCorrect,score,remark,submitTimes);
        try{
            session.beginTransaction();
            session.save(homeworkSubmit);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally{
            HibernateUtils.closeSession(session);
        }
    }

    public void editHWSubmit(Integer hid, Integer sid, Integer fid, Timestamp submitTime,
                             String submitContent, Integer isCorrect, Float score, String remark, Integer submitTimes){
        Session session = HibernateUtils.getSession();
        try {
            HomeworkSubmitPK homeworkSubmitPK = new HomeworkSubmitPK();
            homeworkSubmitPK.setSid(sid);
            homeworkSubmitPK.setHid(hid);
            HomeworkSubmit homeworkSubmit = null;
            session.beginTransaction();
            homeworkSubmit = (HomeworkSubmit) session.get(HomeworkSubmit.class, homeworkSubmitPK);
            homeworkSubmit.setFid(fid);
            homeworkSubmit.setSubmitTime(new Timestamp(System.currentTimeMillis()));
            homeworkSubmit.setSubmitContent(submitContent);
            homeworkSubmit.setIsCorrect(isCorrect);
            homeworkSubmit.setSubmitTimes(submitTimes);

            session.update(homeworkSubmit);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public HomeworkSubmit findHKSubmitByHidSid(Integer hid, Integer sid) {
        Session session = HibernateUtils.getSession();
        HomeworkSubmit homeworkSubmit = null;
        try {
            HomeworkSubmitPK homeworkSubmitPK = new HomeworkSubmitPK();
            homeworkSubmitPK.setHid(hid);
            homeworkSubmitPK.setSid(sid);
            homeworkSubmit = session.load(HomeworkSubmit.class, homeworkSubmitPK);
            return homeworkSubmit;
        } catch (Exception e) {
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public static void main(String args[]){
        StudentHWSubmitDao submitDao = new StudentHWSubmitDao();
        //Timestamp timestamp = Timestamp.valueOf("2017-01-02 11:00:00");

        //submitDao.addHWSubmit(1,14211106,1,timestamp,"123.txt","1234",1, Float.valueOf(10),"123");
        //submitDao.findByHidSid(5,14211106);
        HomeworkSubmit homeworkSubmit = submitDao.findHKSubmitByHidSid(5,14211106);
        System.out.println(homeworkSubmit.getSubmitTime());
    }
}
