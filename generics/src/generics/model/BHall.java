package generics.model;

import java.util.ArrayList;
import java.util.List;

public class BHall implements Hall {

    private List<Shop> shops;

    public BHall(){}

    public BHall(List<Shop> shops){this.shops = shops;}

    @Override
    public List<Shop> getShops() {
        if(this.shops == null) this.shops = new ArrayList<>();
        return shops;
    }

    @Override
    public void setShop(Shop shop) {
        this.shops.add(shop);
    }

    @Override
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
