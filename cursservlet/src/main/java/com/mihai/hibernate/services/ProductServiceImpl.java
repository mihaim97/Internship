package com.mihai.hibernate.services;

import com.mihai.hibernate.dao.ProductDAO;
import com.mihai.hibernate.entity.Product;

import javax.inject.Inject;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductDAO productDAO;

    @Override
    public List<Product> queryProduct() {
        return productDAO.queryProduct();
    }

    @Override
    public void saveOrUpdateProduct(Product prod) {
        productDAO.saveOrUpdateProduct(prod);
    }
}
