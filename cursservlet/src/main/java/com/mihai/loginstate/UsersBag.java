package com.mihai.loginstate;

import com.mihai.db.ProductsDB;

import java.util.*;

public class UsersBag {

    public static UsersBag instance = new UsersBag();

    private UsersBag(){}

    private Map<String, UserBag> usersAndProducts;

    public void addNewUsersInBag(String user, UserBag bag){
        initializeUsersAndProducts();
        this.usersAndProducts.put(user, bag);
    }

    public void addInUserBag(String user, String product){
        initializeUsersAndProducts();
        this.usersAndProducts.get(user).addProduct(product);
    }

    public int countUserProducts(String user){
        return this.usersAndProducts.get(user).getProducts().size();
    }

    public boolean userExist(String user){
        initializeUsersAndProducts();
        if(usersAndProducts.containsKey(user))
            return true;
        return false;
    }

    public void initializeUsersAndProducts(){
        if(usersAndProducts == null){usersAndProducts = new HashMap<>(); }
    }

    public UserBag getUserBag(String user){
        return this.usersAndProducts.get(user);
    }

    public boolean userHasProductThenDelete(String user, String product){
        if(userExist(user)){
            UserBag userBag = getUserBag(user);
            return userBag.ifProductExistDelete(product);
        }
        return false;
    }

}
