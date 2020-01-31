package generics.model;

import java.util.List;

public class Mall<T extends Hall> {

    private T mall;

    public Mall(){}

    public Mall(T mall){this.mall = mall ;}

    public T getMall() {
        return mall;
    }
}
