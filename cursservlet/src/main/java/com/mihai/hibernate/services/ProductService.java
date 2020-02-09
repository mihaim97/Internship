package com.mihai.hibernate.services;

import com.mihai.hibernate.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> queryProduct();
    public void saveOrUpdateProduct(Product prod);
}
