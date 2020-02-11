package com.mihai.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "producttype")
public class ProductType implements Serializable {

    @Id
    private String type;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<Product> productOfThisType;

    public ProductType(){}

    public ProductType(String type, List<Product> productOfThisType) {
        this.type = type;
        this.productOfThisType = productOfThisType;
    }

    public ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Product> getProductOfThisType() {
        return productOfThisType;
    }

    public void setProductOfThisType(List<Product> productOfThisType) {
        this.productOfThisType = productOfThisType;
    }
}
