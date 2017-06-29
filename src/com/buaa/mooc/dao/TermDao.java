package com.buaa.mooc.dao;

import com.buaa.mooc.entity.Term;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class TermDao {
    /**
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 是否成功
     * @author huxia
     * @功能 在Term表中增加一条记录
     */
    public boolean AddTerm(Date startTime, Date endTime, Integer year, String quarter, Integer weeks) {
        Session session = HibernateUtils.getSession();
        try {
            session.beginTransaction();
            Term term = new Term();
            term.setStartDate(startTime);
            term.setEndDate(endTime);
            term.setYear(year);
            term.setQuarter(quarter);
            term.setWeeks(weeks);
            session.save(term);
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

    /**
     * @param termId 学期ID
     * @return 是否成功
     * @author huxia
     * @功能 从Term表中删除一条记录
     */
    public boolean DeleteTerm(Integer termId) {
        Session session = HibernateUtils.getSession();
        try {
            session.beginTransaction();

            Term term = (Term) session.load(Term.class, termId);
            session.delete(term);
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

    /**
     * @param termId    要更新的记录的ID
     * @param startTime 要将该记录的开始时间更新成的开始时间
     * @param endTime   要将该记录的开始时间更新成的结束时间
     * @return
     * @author huxia
     * @功能 更新Term表中的一条记录
     */
    public boolean UpdateTerm(Integer termId, Date startTime, Date endTime) {
        Session session = HibernateUtils.getSession();
        try {
            session.beginTransaction();

            Term term = (Term) session.load(Term.class, termId);
            term.setStartDate(startTime);
            term.setEndDate(endTime);
            session.update(term);
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

    /**
     * @return 所有记录的List，List的元素类型为Term
     * @author huxia
     * @功能 查找Term表中所有记录
     */
    public List FindAll() {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            String hql = "from Term";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    /**
     * @return 最后一条记录
     * @author huxia
     * @功能 查找Term表中最后一条记录
     */
    public Term FindLast() {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            String hql = "from Term order by id desc";
            Query query = session.createQuery(hql);
            query.setFirstResult(0);
            query.setMaxResults(1);
            return (Term) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
	
	/*
	public static void main(String[] args) {
		TermDao termDao = new TermDao();
		List allTerm = termDao.FindAll();
		Term lastTerm = termDao.FindLast();
		
		for(Object term : allTerm) {
			System.out.println(((Term)term).getStartDate());
		}
		System.out.println(lastTerm.getStartDate());
		
	}
	*/
}
