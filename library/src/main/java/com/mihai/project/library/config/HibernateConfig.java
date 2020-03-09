package com.mihai.project.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean(name = "dataSource")
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
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.mihai.project.library" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManagerBean = new HibernateTransactionManager();
        transactionManagerBean.setSessionFactory(sessionFactory().getObject());
        return transactionManagerBean;
    }

    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        return properties;
    }
}
