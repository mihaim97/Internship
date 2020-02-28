package com.mihai.project.library.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class Hibernate {


    @Bean
    public SessionFactory sessionFactory() throws IOException {
     /*   Properties properties = new Properties();
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        properties.put("current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        properties.put("hibernate.show_sql", "true");
        properties.put("hbm2ddl.auto", "auto");
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan(new String[]{"com.mihai.project.library"});
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(properties);
        //sessionFactoryBean.setAnnotatedClasses(new Class[]{Book.class, Author.class, Tag.class, User.class});
        sessionFactoryBean.afterPropertiesSet();
        SessionFactory sf = sessionFactoryBean.getObject();
        return sf;
     */   return new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }


/*    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/library");
        dataSource.setUsername("mihai");
        dataSource.setPassword("mihai");
        System.out.println("DataSource: " + dataSource);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        Properties properties = new Properties();
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hbm2ddl.auto", "auto");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(properties);
        sessionFactory.setPackagesToScan(new String[]{"com.mihai.project.library"});
        return sessionFactory;
    }*/

   /* @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws IOException {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        return transactionManager;
    }
*/

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws IOException {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        return transactionManager;
    }
}
