package com.mihai.db;

import com.mihai.util.DBProperties;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ProductsDB {

     //public static ProductsDB instance = new ProductsDB();

    private Map<String, List<String>> products;

    public ProductsDB(){
        checkInitialize();
    }

    private void initializeProducts(){
        products = new HashMap<>();
        products.put(DBProperties.carsKey, initializeCar());
        products.put(DBProperties.pcKey, initializePC());
    }

    private List<String> initializeCar(){
        List<String> cars = new ArrayList<>();
        cars.add("Dacia");
        cars.add("Renault");
        cars.add("BMW");
        return cars;
    }

    private List<String> initializePC(){
        List<String> pc = new ArrayList<>();
        pc.add("DELL");
        pc.add("HP");
        pc.add("LENOVO");
        return pc;
    }

    private void checkInitialize(){
        if(products == null) initializeProducts();
    }

    public Map<String, List<String>> getProducts(){
       // checkInitialize();
        return this.products;
    }

    public void addCarProduct(String product){
      //  checkInitialize();
        getProducts().get(DBProperties.carsKey).add(product);
    }

}
