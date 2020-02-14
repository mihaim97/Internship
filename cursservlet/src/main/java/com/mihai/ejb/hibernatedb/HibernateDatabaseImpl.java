package com.mihai.ejb.hibernatedb;

import com.mihai.ejb.Database;
import com.mihai.hibernate.entity.Order;
import com.mihai.hibernate.entity.Product;
import com.mihai.hibernate.entity.User;
import com.mihai.hibernate.services.ProductService;
import com.mihai.qualifier.HibernateDB;
import com.mihai.util.DBProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Qualifier;
import java.sql.*;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@HibernateDB
public class HibernateDatabaseImpl implements Database {

    @Inject
    private ProductService productService;

    @Override
    public void getMsg() {

    }

    @Override
    public List<Product> queryProducts() {
        return productService.queryProduct();
    }

    @Override
    public void saveProduct() {

    }

    @Override
    public boolean findUserByCredentials(String username, String password) {
        return false;
    }

    @Override
    public void addProduct() {

    }

    @Override
    public void registerAnOrder(String user, List<String> products) {

    }

    @Override
    public List<Order> getUserOrders(String user) {
        return null;
    }

    @Override
    public Product queryProduct(String name) {
        return null;
    }
}
