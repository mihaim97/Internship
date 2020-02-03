package generics.shop;

import java.util.List;

public interface Shop<T> {
    public List<T> getProducts();
    public void showProducts();
}
