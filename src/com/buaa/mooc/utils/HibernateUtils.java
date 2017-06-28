/**
 * Created by huxia on 2017/6/27.
 */
package com.buaa.mooc.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    private final static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    static {
        registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session){
        if (session != null){
            if (session.isOpen()){
                session.close();
            }
        }
    }
}