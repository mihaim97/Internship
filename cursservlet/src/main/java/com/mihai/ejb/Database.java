package com.mihai.ejb;

import com.mihai.hibernate.entity.Product;

import java.util.List;

public interface Database {

    public void getMsg();

    public List<Product> queryProducts();

    public void queryProduct(int id);

    public void saveProduct();

}
