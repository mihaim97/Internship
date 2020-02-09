package com.mihai.hibernate.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HibernateSessionImpl implements HibernateSession {

    private SessionFactory factory;

    public HibernateSessionImpl(){
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    public Session createSession() {
        return factory.openSession();
    }

}
