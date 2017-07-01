package com.buaa.mooc.dao;

import com.buaa.mooc.entity.File;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;

/**
 * Created by huxia on 2017/6/28.
 */
public class FileDao {

    public boolean AddFile(String filename, Integer id) {
        Session session = HibernateUtils.getSession();
        ;
        try {
            session.beginTransaction();
            File file = new File();
            file.setFilename(filename);
            file.setUploadby(id);
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
