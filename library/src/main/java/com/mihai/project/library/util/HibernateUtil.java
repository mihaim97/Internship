package com.mihai.project.library.util;

import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            //sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }

    public static <T> T getUniqueResult(List<T> resultList) {
        if (resultList == null || resultList.isEmpty() || resultList.size() > 1) {
            return null;
        }
        return (T) resultList.get(0);
    }


}
