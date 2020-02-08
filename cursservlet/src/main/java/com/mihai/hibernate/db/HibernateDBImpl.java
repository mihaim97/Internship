package com.mihai.hibernate.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HibernateDBImpl implements HibernateDB {

    private SessionFactory factory;

    public HibernateDBImpl(){
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    public Session createSession() {
        return factory.openSession();
    }

}
