package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.DishDTO;
import dk.bepeaked.bodybook.R;

/**
 * Created by sebho on 14-11-2016.
 */

public class DietDAO {
    public ArrayList<DishDTO> getDishes(Activity act) throws IOException, JSONException {
        ArrayList<DishDTO> result = new ArrayList<DishDTO>();
        InputStream is = act.getResources().openRawResource(R.raw.dish);
        byte b[] = new byte[is.available()]; // kun små filer
        is.read(b);
        String str = new String(b, "UTF-8");
        JSONObject json = new JSONObject();
        JSONObject jA = new JSONObject(str);
        JSONArray category;
        JSONObject dish;

        for (int i = 0; i < jA.length(); i++){
            category = jA.getJSONArray("Morgenmad");
            switch (i){
                case 0: category = jA.getJSONArray("Morgenmad");
                    break;
                case 1: category = jA.getJSONArray("Frokost");
                    break;
                case 2: category = jA.getJSONArray("Aftensmad");
                    break;
                case 3: category = jA.getJSONArray("Snack");
                    break;
            }
            for (int j = 0; j < category.length(); j++) {
                dish = category.getJSONObject(j);
                String name = dish.getString("name");
                String imagePath = dish.getString("image");
                String deskShort = dish.getString("desk_short");
                String deskFull = dish.getString("desk_full");
                JSONArray ingredientList = dish.getJSONArray("Ingredients");
                ArrayList<String[]> ingredients = new ArrayList<String[]>();
                for (int k = 0; k < ingredientList.length(); k++){
                    JSONObject ingredient = ingredientList.getJSONObject(k);
                    ingredients.add(new String[]{ingredient.getString("name"), ingredient.getString("stats")});
                }
                result.add(new DishDTO(i, name, imagePath, deskShort, deskFull, ingredients));
            }
        }
        return result;
    }
}