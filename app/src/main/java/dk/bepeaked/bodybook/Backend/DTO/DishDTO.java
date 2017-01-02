package dk.bepeaked.bodybook.Backend.DTO;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sebho on 14-11-2016.
 */

public class DishDTO {
    //Type: 0 = Morgenmad, 1 = Frokost, 2 = Aftensmad, 3 = Snack.
    private int type;
    private String name, imagePath, deskShort, deskLong;
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    public DishDTO(int type, String name, String imagePath, String deskShort, String deskLong, ArrayList<String[]> ingredients) {
        this.type = type;
        this.name = name;
        this.imagePath = imagePath;
        this.deskShort = deskShort;
        this.deskLong = deskLong;
        for (int i = 0; i < ingredients.size(); i++){
            String[] ingredient = ingredients.get(i);
            String ingName = ingredient[0];
            String[] stats = ingredient[1].split(",");
            for (int j = 0; j < stats.length; j++) {
                Log.d("Sebbyg", "DishDTO: " + stats[j]);
            }
            this.ingredients.add(new Ingredient(ingName, Integer.parseInt(stats[0]), Integer.parseInt(stats[1]),
                    Integer.parseInt(stats[2]), Integer.parseInt(stats[3]), Integer.parseInt(stats[4])));
        }

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
