package generics;

import generics.model.*;
import generics.shop.KFC;

public class App {

    public static void main(String[] args) {
       Mall<Hall> vivo = new Mall<>(new BHall());

       Shop<Food> kfc = new KFC();
       kfc.getProducts().add(new Food("Aripioare"));
       kfc.getProducts().add(new Food("Cartofi"));
       Shop<HealthyFood> saladBox = new SaladBox();

       vivo.getMall().getShops().add(kfc);
       vivo.getMall().getShops().add(saladBox);

       vivo.getMall().getShops().get(0).showProducts();

    }
}
