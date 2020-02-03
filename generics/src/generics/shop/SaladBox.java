package generics.shop;

import generics.food.Food;
import generics.food.HealthyFood;

import java.util.ArrayList;
import java.util.List;

public class SaladBox implements Shop<Food> {

    private List<Food> foods;

    @Override
    public List<Food> getProducts() {
        if(foods == null) this.foods = new ArrayList<Food>();
        return foods;
    }

    @Override
    public void showProducts() {
        foods.stream().forEach((food)->{System.out.println(food);});
    }

}
