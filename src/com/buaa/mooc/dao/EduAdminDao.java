package com.buaa.mooc.dao;

import org.hibernate.Session;

import com.buaa.mooc.entity.EduAdmin;
import com.buaa.mooc.utils.HibernateUtils;

public class EduAdminDao {
	public boolean isExistByEidAndPassword(Integer eid, String password) {
		Session session = HibernateUtils.getSession();
		EduAdmin ead = null;
        try {
            session.beginTransaction();  
              
            ead = (EduAdmin)session.get(EduAdmin.class, eid);
            session.getTransaction().commit();
        }catch(Exception e) {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }finally {  
        	HibernateUtils.closeSession(session);
        }
        
        if(ead == null || !ead.getPassword().trim().equals(password.trim())) {
        	return false;
        } else {
        	return true;
        }
	}

    public EduAdmin findById(Integer eid) {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            return session.load(EduAdmin.class, eid) != null ? session.load(EduAdmin.class, eid) : null;
        }catch(Exception e) {
            return null;
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
}
