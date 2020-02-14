package com.mihai.ejb.servicedb;

import com.mihai.ejb.Database;
import com.mihai.ejb.DatabaseService;
import com.mihai.hibernate.entity.Order;
import com.mihai.hibernate.entity.Product;
import com.mihai.qualifier.JDBCDatabase;
import com.mihai.qualifier.JDBCDatabaseService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@JDBCDatabaseService
public class JDBCDatabaseServiceImpl implements DatabaseService {

    @Inject
    @JDBCDatabase
    private Database db;

    @Override
    public void getMsg() {
        db.getMsg();
    }

    @Override
    public List<Product> queryProducts() {
       return db.queryProducts();
    }

    @Override
    public Product queryProduct(String name) {
       return db.queryProduct(name);
    }

    @Override
    public void saveProduct() {
        db.saveProduct();
    }

    @Override
    public boolean findUserByCredentials(String username, String password) {
      return db.findUserByCredentials(username, password);
    }

    @Override
    public void addProduct() {
        db.addProduct();
    }

    @Override
    public void registerAnOrder(String user, List<String> products) {
        db.registerAnOrder(user, products);
    }

    @Override
    public List<Order> getUserOrders(String user) {
        return  db.getUserOrders(user);
    }
}
