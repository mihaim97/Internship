package com.mihai.hibernate.db;

import org.hibernate.Session;

public interface HibernateDB {

    public Session createSession();
}
