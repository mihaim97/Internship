package generics.clothes;

import generics.material.Material;

import java.util.ArrayList;
import java.util.List;

public class TShirt implements  Clothes {

    private String name;

    private List<Material> materials;

    public TShirt(){this.name = "T-Shirt";}

    public TShirt(String name){this.name = name;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Material> getMaterials() {
        return this.materials;
    }

    @Override
    public void setClothes(List<Material> materials) {
        checkMaterials();
        this.materials = materials;
    }

    @Override
    public void addClothes(Material material) {
        checkMaterials();
        this.materials.add(material);
    }

    @Override
    public void showClothes() {
        this.materials.stream().forEach((mat)->{System.out.println(mat);});
    }

    private void checkMaterials(){
        if(materials == null){this.materials = new ArrayList<>();}
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "name='" + name + '\'' +
                '}';
    }
}
