package generics.food;

import generics.ingredient.Ingredient;

import java.util.List;

public interface Food {

    public List<Ingredient> getIngredients();

    public void setIngredients(List<Ingredient> ingredients);

    public void addIngredient(Ingredient ing);

    public void showIngredients();
}
