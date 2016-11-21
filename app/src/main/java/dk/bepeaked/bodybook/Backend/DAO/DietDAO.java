package dk.bepeaked.bodybook.Backend.DAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import dk.bepeaked.bodybook.Backend.DTO.DishDTO;
import dk.bepeaked.bodybook.R;

/**
 * Created by sebho on 14-11-2016.
 */

public class DietDAO {
    private void createJSON() throws IOException, JSONException {
        JSONObject json = new JSONObject();
        JSONArray jA = json.getJSONArray(String.valueOf(R.raw.Dish));
        JSONArray category;
        JSONArray dish;

//        Iterator<String> iterator =
        for (int i = 0; i < jA.length(); i++){
            category = jA.getJSONArray(i);
            for (int j = 0; j < category.length(); j++) {
                dish = category.getJSONArray(i);
                String name = dish.getString(0);
                String imagePath = dish.getString(1);
                JSONArray ingredientList = dish.getJSONArray(2);
                ArrayList<String[]> ingredients = new ArrayList<String[]>();
                for (int k = 0; k < ingredientList.length(); k++){
                    JSONObject ingredient = ingredientList.getJSONObject(k);
                    ingredients.add(new String[]{ingredient.getString("name"), ingredient.getString("stats")});
                }
                DishDTO dto = new DishDTO(name, imagePath, ingredients);
            }
        }
    }
}
