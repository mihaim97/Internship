package com.mihai.project.library.util;

import com.mihai.project.library.contralleradvice.exception.NoUniqueResult;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }

    public static <T> T getUniqueResult(List<T> resultList) throws NoUniqueResult {
        if (resultList == null || resultList.isEmpty()) {
            return null;
        }
        if (resultList.size() > 1) {
            throw new NoUniqueResult("Too many results");
        }
        return (T) resultList.get(0);
    }


}
