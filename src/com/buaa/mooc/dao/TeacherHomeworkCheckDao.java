package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Homework;
import com.buaa.mooc.entity.HomeworkSubmit;
import com.buaa.mooc.entity.HomeworkSubmitPK;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/7/2.
 */
public class TeacherHomeworkCheckDao {
    public List<HomeworkSubmit> findByHid(Integer hid) {
        Session session = HibernateUtils.getSession();
        List results = null;
        List<HomeworkSubmit> homeworkSubmits = null;
        try{
            homeworkSubmits = new ArrayList<>();
            String hql="select h from HomeworkSubmit as h where h.pk.hid = :hid";
            Query query = session.createQuery(hql);
            query.setParameter("hid", hid);
            results = query.list();
            if (results != null && results.size()>0) {
                HomeworkSubmit c;
                for (Object obj : results) {
                    c = (HomeworkSubmit) obj;
                    homeworkSubmits.add(c);
                }
            }
            return homeworkSubmits;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            HibernateUtils.closeSession(session);
        }
    }

    public void EditHomeworkCheck(Integer hid,Integer sid, float score,String remark) {
        Session session = HibernateUtils.getSession();
        try {
            session.beginTransaction();
            HomeworkSubmit submit = findByPK(hid,sid);
            submit.setIsCorrect(1);
            submit.setScore(score);
            submit.setRemark(remark);
            session.update(submit);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
    public HomeworkSubmit findByPK(Integer hid,Integer sid) {
        Session session = HibernateUtils.getSession();
        try{
            HomeworkSubmitPK pk = new HomeworkSubmitPK();
            pk.setHid(hid);
            pk.setSid(sid);
            return session.load(HomeworkSubmit.class,pk);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            HibernateUtils.closeSession(session);
        }
    }
}
