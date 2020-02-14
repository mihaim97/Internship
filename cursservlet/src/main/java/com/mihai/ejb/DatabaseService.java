package com.mihai.ejb;

import com.mihai.hibernate.entity.Order;
import com.mihai.hibernate.entity.Product;

import java.util.List;

public interface DatabaseService {
    public void getMsg();

    public List<Product> queryProducts();

    public Product queryProduct(String name);

    public void saveProduct();

    // user method

    public boolean findUserByCredentials(String username, String password);

    // Sf user method

    // product method

    public void addProduct();

    public void registerAnOrder(String user, List<String> products);

    public List<Order> getUserOrders(String user);

    // Sf product method
}
