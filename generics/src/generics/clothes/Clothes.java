package generics.clothes;

import generics.ingredient.Ingredient;
import generics.material.Material;

import java.util.List;

public interface Clothes {

    public List<Material> getMaterials();

    public void setClothes(List<Material> ingredients);

    public void addClothes(Material ing);

    public void showClothes();
}
