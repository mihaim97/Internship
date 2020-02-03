package generics.food;

import generics.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class HealthyFood implements Food{

    private String name;

    private List<Ingredient> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public void addIngredient(Ingredient ing){
        if(ingredients == null) this.ingredients = new ArrayList<>();
        this.ingredients.add(ing);
    }

    @Override
    public void showIngredients(){
        if(ingredients == null) return;
        ingredients.stream().forEach((ing) -> {System.out.print(ing);});
    }

    @Override
    public String toString() {
        return "HealthyFood{" +
                "name='" + name + '\'' +
                '}';
    }
}
