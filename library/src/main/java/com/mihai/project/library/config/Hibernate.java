package com.mihai.project.library.config;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.hibernate.cfg.Configuration;

@Component
public class Hibernate {

    @Bean
    public SessionFactory sessionFactory(){
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

}
