package com.mihai.ejb;

import com.mihai.hibernate.entity.Product;

import java.util.List;

public interface Database {

    public void getMsg();

    public List<Product> queryProducts();

    public void queryProduct(int id);

    public void saveProduct();

    // user method

    public boolean findUserByCredentials(String username, String password);

    // Sf user method

    // product method

    public void addProduct();

    public void registerAnOrder(String user, List<String> products);

    public void getUserOrders(String user);

    // Sf product method

}
