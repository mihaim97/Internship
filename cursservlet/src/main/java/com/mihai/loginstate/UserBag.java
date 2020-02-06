package com.mihai.loginstate;

import java.util.ArrayList;
import java.util.List;

public class UserBag {

    private List<String> products;

    public  UserBag(){
        products = new ArrayList<>();
    }

    public void addProduct(String product){
        this.products.add(product);
    }

    public void removeProduct(String product){
        this.products.remove(product);
    }

    public List<String> getProducts(){return this.products;}

}
