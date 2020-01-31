package generics.model;

import java.util.ArrayList;
import java.util.List;

public class Food {

    private String name;

    private List<Ingredient> ingredients;

    public Food(){}

    public Food(String name){this.name=name;}

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

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                '}';
    }
}
