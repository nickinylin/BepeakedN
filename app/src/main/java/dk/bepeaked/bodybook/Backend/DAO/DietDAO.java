package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;

import java.io.Serializable;
import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.DishDTO;
import dk.bepeaked.bodybook.Backend.DTO.Ingredient;
import io.realm.RealmList;

/**
 * Created by sebho on 14-11-2016.
 */

public class DietDAO implements Serializable {
    ExcelDAO dao = new ExcelDAO();
    private ArrayList<DishDTO> savedDTO = new ArrayList<>();

    public ArrayList<DishDTO> getDTOS(){
        return savedDTO;
    }

    public void getDishes(Activity act, String file){
        ArrayList<DishDTO> dtos = new ArrayList<>();
        ArrayList<String[]> data = dao.readCsv(act, file);
        ArrayList<String[]> cleanData = new ArrayList<>();
        ArrayList<ArrayList<String>> cleanData2 = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            cleanData.add(data.get(i));
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
        for(int i = 0; i < cleanData2.size(); i++){
            DishDTO dto = new DishDTO();
            int indexStart;
            int indexEnd = 0;
            String[] nameSplit;
            RealmList<Ingredient> ingredients = new RealmList<>();
            String name;
            if(cleanData2.get(i).get(0).contains("Morgenmad") || cleanData2.get(i).get(0).contains("Frokost")
                    || cleanData2.get(i).get(0).contains("Aftensmad") || cleanData2.get(i).get(0).contains("Snack")){
                indexStart = i;
                nameSplit = cleanData2.get(i).get(0).split(":");
                name = nameSplit[1];
                for(int j = i; j < cleanData2.size(); j++){
                    if(cleanData2.get(j).get(0).contains("Total")){
                        indexEnd = j;
                        break;
                    }
                }
                if(cleanData2.get(i).get(0).contains("Morgenmad")){
                    dto.setType(0);
                }else if(cleanData2.get(i).get(0).contains("Frokost")){
                    dto.setType(1);
                }else if(cleanData2.get(i).get(0).contains("Aftensmad")){
                    dto.setType(2);
                }else if(cleanData2.get(i).get(0).contains("Snack")){
                    dto.setType(3);
                }

                for(int k = indexStart+2; k < indexEnd; k++){
                    String inName = cleanData2.get(k).get(0);
                    int weight = Integer.parseInt(cleanData2.get(k).get(1));
                    int protein = Integer.parseInt(cleanData2.get(k).get(2));
                    int fat = Integer.parseInt(cleanData2.get(k).get(3));
                    int carbon = Integer.parseInt(cleanData2.get(k).get(4));
                    int cal = Integer.parseInt(cleanData2.get(k).get(5));
                    ingredients.add(new Ingredient(inName, weight, protein, fat, carbon, cal));
                }
                dto.setName(name);
                dto.setIngredients(ingredients);
                dtos.add(dto);
            }
        }
            savedDTO = dtos;
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