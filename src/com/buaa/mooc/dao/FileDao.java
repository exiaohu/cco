package com.buaa.mooc.dao;

import com.buaa.mooc.entity.File;
import com.buaa.mooc.utils.HibernateUtils;
import org.hibernate.Session;

/**
 * Created by huxia on 2017/6/28.
 */
public class FileDao {

    public Integer AddFile(String filename, Integer id) {
        Session session = HibernateUtils.getSession();
        try {
            session.beginTransaction();
            File file = new File();
            file.setFilename(filename);
            file.setUploadby(id);
            session.save(file);
            session.getTransaction().commit();
            //System.out.println("fid===" + file.getId());
            return file.getId();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public File getFileById(Integer fid){
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            return session.load(File.class, fid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    static public void main(String[] args) {
        FileDao fileDao =new FileDao();
        Integer id = fileDao.AddFile("gehsa", 1);
        System.out.println(id);
    }
}
