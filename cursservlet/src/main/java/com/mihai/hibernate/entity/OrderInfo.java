package com.mihai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "orderinfo")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    public OrderInfo(){}

    public OrderInfo(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public OrderInfo(int id, Order order, Product product) {
        this.id = id;
        this.order = order;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
