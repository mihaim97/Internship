package generics.model;

import java.util.List;

public class SaladBox implements Shop<HealthyFood> {

    private List<HealthyFood> foods;

    @Override
    public List<HealthyFood> getProducts() {
        return foods;
    }

    @Override
    public void showProducts() {

    }

}
