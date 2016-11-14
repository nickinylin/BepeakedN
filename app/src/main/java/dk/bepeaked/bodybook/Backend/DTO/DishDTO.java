package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;

/**
 * Created by sebho on 14-11-2016.
 */

public class DishDTO {
    private int ID;
    private String name;
    private ArrayList<IngredientDTO> ingredients;

    public DishDTO (int ID, String name, ArrayList<IngredientDTO> ingredients) {
        this.ID = ID;
        this.name = name;
        this.ingredients = ingredients;
    }
}
