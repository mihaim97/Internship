package generics;

import generics.clothes.Clothes;
import generics.clothes.TShirt;
import generics.food.Chicken;
import generics.food.Food;
import generics.food.Hamburger;
import generics.food.HealthyFood;
import generics.model.*;
import generics.shop.KFC;
import generics.shop.SaladBox;
import generics.shop.Shop;
import generics.shop.Zara;

public class App {

    public static void main(String[] args) {
       Mall<Hall> vivo = new Mall<>(new BHall());

       Shop<Food> kfc = new KFC();
       Shop<Food> saladBox = new SaladBox();
       Shop<Clothes> zara = new Zara();

       kfc.getProducts().add(new Chicken());
       kfc.getProducts().add(new Hamburger());

       zara.getProducts().add(new TShirt());

       vivo.getMall().getShops().add(kfc);
       vivo.getMall().getShops().add(saladBox);
       vivo.getMall().getShops().add(zara);

       vivo.getMall().getShops().get(0).showProducts();
       vivo.getMall().getShops().get(2).showProducts();

    }
}
