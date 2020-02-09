package com.mihai.hibernate.dao;

import com.mihai.hibernate.entity.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> queryProduct();
    public void saveOrUpdateProduct(Product prod);
}
