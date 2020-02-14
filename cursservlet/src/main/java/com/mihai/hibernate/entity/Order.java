package com.mihai.hibernate.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "expDate")
    private Date expDate;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @OneToMany(mappedBy = "order")
    private List<OrderInfo> orderInfo;

    public Order(){}

    public Order(int id, Date orderDate, Date expDate, User owner, List<OrderInfo> orderInfo) {
        this.id = id;
        this.orderDate = orderDate;
        this.expDate = expDate;
        this.owner = owner;
        this.orderInfo = orderInfo;
    }

    public Order(int id, Date orderDate, Date expDate, User owner) {
        this.id = id;
        this.orderDate = orderDate;
        this.expDate = expDate;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<OrderInfo> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderInfo> orderInfo) {
        this.orderInfo = orderInfo;
    }
}
