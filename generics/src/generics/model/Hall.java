package generics.model;

import java.util.List;

public interface Hall {
    public List<Shop> getShops();
    public void setShop(Shop shop);
    public void setShops(List<Shop> shops);

}
