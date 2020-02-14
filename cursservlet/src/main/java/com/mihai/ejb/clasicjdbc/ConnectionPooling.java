package com.mihai.ejb.clasicjdbc;

import com.mihai.util.DBProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPooling {

    private static HikariDataSource dataSource;

    static{
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DBProperties.connectionString);
        config.setUsername(DBProperties.user);
        config.setPassword(DBProperties.password);
        config.setDriverClassName(DBProperties.driver);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();

    }

}
