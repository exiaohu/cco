package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Course;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class CourseDao {

    //�������ӿγ�
    public void addCourse(String cname, Integer credit, Date beginDate, Date endDate, String address, Integer termId) {
        Session session = HibernateUtils.getSession();
        Course course = new Course();
        course.setCname(cname);
        course.setCredit(credit);
        course.setBeginDate(beginDate);
        course.setEndDate(endDate);
        course.setAddress(address);
        course.setTermId(termId);
        try {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    //��ʦ�༭�γ̴��
    public void editCourse(Integer cid, String outline, String filename) {
        Session session = HibernateUtils.getSession();
        try {
            Course course;
            session.beginTransaction();
            course = (Course) session.get(Course.class, cid);
            course.setOutline(outline);
            course.setAccessory(filename);
            session.update(course);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public Course findByCid(Integer cid) {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            return session.load(Course.class, cid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    /**
     * @return 所有记录的List，List的元素类型为Term
     * @author huxia
     * @功能 查找Term表中所有记录
     */
    public List<Course> FindAll() {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            String hql = "from Course";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public boolean editCourseByAdmin(Integer cid, Integer termId, String cname, Date beginDate, Date endDate, Integer credit, String address) {
        Session session = HibernateUtils.getSession();
        try {
            Course course;
            session.beginTransaction();
            course = (Course) session.get(Course.class, cid);
            course.setTermId(termId);
            course.setCname(cname);
            course.setBeginDate(beginDate);
            course.setEndDate(endDate);
            course.setCredit(credit);
            course.setAddress(address);
            session.update(course);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
