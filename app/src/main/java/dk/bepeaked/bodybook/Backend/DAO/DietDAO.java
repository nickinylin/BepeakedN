package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import dk.bepeaked.bodybook.Backend.DTO.DishDTO;

/**
 * Created by sebho on 14-11-2016.
 */

public class DietDAO {
    ExcelDAO dao = new ExcelDAO();
    public ArrayList<DishDTO> getDishes(Activity act){
        ArrayList<DishDTO> dtos = new ArrayList<>();
        ArrayList<String[]> data = dao.readCsv(act);
        ArrayList<String[]> cleanData = new ArrayList<>();
        ArrayList<ArrayList<String>> cleanData2 = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            cleanData.add(data.get(i)[0].split(";"));
        }
        for(int i = 0; i < cleanData.size(); i++){
            ArrayList<String> string = new ArrayList<>();
            for(int j = 0; j < cleanData.get(i).length; j++){
                if (cleanData.get(i)[j].equals("") || cleanData.get(i)[j].equals(" ") || cleanData.get(i)[j].equals(null)) {

                }else{
                    string.add(cleanData.get(i)[j]);
                }
            }
            if(string.size() > 0){
                cleanData2.add(string);
            }
        }
        for(int i = 0; i < cleanData2.size(); i++) {
            Log.d("SEBBY", "getDishes: " + Arrays.toString(cleanData2.get(i).toArray()));
        }
            return dtos;
    }

//    public ArrayList<DishDTO> getDishes(Activity act) throws IOException, JSONException {
//        ArrayList<DishDTO> result = new ArrayList<DishDTO>();
//        InputStream is = act.getResources().openRawResource(R.raw.abc123);
//        byte b[] = new byte[is.available()]; // kun små filer
//        is.read(b);
//        String str = new String(b, "UTF-8");
//        JSONObject json = new JSONObject();
//        JSONObject jA = new JSONObject(str);
//        JSONArray category;
//        JSONObject dish;
//
//        for (int i = 0; i < jA.length(); i++){
//            category = jA.getJSONArray("Morgenmad");
//            switch (i){
//                case 0: category = jA.getJSONArray("Morgenmad");
//                    break;
//                case 1: category = jA.getJSONArray("Frokost");
//                    break;
//                case 2: category = jA.getJSONArray("Aftensmad");
//                    break;
//                case 3: category = jA.getJSONArray("Snack");
//                    break;
//            }
//            for (int j = 0; j < category.length(); j++) {
//                dish = category.getJSONObject(j);
//                String name = dish.getString("name");
//                String imagePath = dish.getString("image");
//                String deskShort = dish.getString("desk_short");
//                String deskFull = dish.getString("desk_full");
//                JSONArray ingredientList = dish.getJSONArray("Ingredients");
////                RealmList<String[]> ingredients = new RealmList<String[]>();
////                for (int k = 0; k < ingredientList.length(); k++){
////                    JSONObject ingredient = ingredientList.getJSONObject(k);
////                    ingredients.add(new String[]{ingredient.getString("name"), ingredient.getString("stats")});
////                }
////                result.add(new DishDTO(i, name, imagePath, deskShort, deskFull, ingredients));
//            }
//        }
//        return result;
//    }
}