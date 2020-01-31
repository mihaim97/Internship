package generics.model;

import java.util.ArrayList;
import java.util.List;

public class HealthyFood {
    private String name;

    private List<Ingredient> ingredients;

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ing){
        if(ingredients == null) this.ingredients = new ArrayList<>();
        this.ingredients.add(ing);
    }

    public void showIngredients(){
        if(ingredients == null) return;
        ingredients.stream().forEach((ing) -> {System.out.print(ing);});
    }
}
