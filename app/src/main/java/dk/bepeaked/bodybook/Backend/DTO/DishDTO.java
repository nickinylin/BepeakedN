package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

public class DishDTO extends RealmObject{
    //Type: 0 = Morgenmad, 1 = Frokost, 2 = Aftensmad, 3 = Snack.
    private int type, imagePath;
    private String name, deskShort, deskLong;
    private RealmList<Ingredient> ingredients = new RealmList<Ingredient>();

    public DishDTO() {}

    public DishDTO(int type, String name, int imagePath, String deskShort, String deskLong, RealmList<Ingredient> ingredients) {
        this.type = type;
        this.name = name;
        this.imagePath = imagePath;
        this.deskShort = deskShort;
        this.deskLong = deskLong;
        this.ingredients = ingredients;


    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getDeskShort() {
        return deskShort;
    }

    public void setDeskShort(String deskShort) {
        this.deskShort = deskShort;
    }

    public String getDeskLong() {
        return deskLong;
    }

    public void setDeskLong(String deskLong) {
        this.deskLong = deskLong;
    }

    public RealmList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        RealmList<Ingredient> result = new RealmList<>();
        for(int i = 0; i < ingredients.size(); i++){
            result.add(ingredients.get(i));
        }
        this.ingredients = result;
    }
}
