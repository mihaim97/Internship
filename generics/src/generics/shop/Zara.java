package generics.shop;

import generics.clothes.Clothes;
import generics.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Zara implements Shop<Clothes> {

    private List<Clothes> clothes;

    @Override
    public List<Clothes> getProducts() {
        if(clothes == null) {this.clothes = new ArrayList<>(); }
        return this.clothes;
    }

    @Override
    public void showProducts() {
        clothes.stream().forEach((cl)->{System.out.println(cl);});
    }
}
