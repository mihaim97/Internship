package generics.shop;

import generics.model.Food;
import generics.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class KFC implements Shop<Food> {

    private List<Food> foods;

    @Override
    public List<Food> getProducts() {
        if(foods == null) this.foods = new ArrayList<Food>();
        return foods;
    }

    @Override
    public void showProducts() {
        for(Food food: foods){System.out.println(food);}
    }


}
