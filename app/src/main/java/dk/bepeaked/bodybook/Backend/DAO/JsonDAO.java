package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import dk.bepeaked.bodybook.Backend.DTO.DishDTO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import io.realm.RealmList;

public class JsonDAO {
    ExerciseDAO dao = new ExerciseDAO();

//    public void getData(Activity act, String code) throws IOException, JSONException {
//        int file = act.getResources().getIdentifier(code, "drawable", act.getPackageName());
//        InputStream is = act.getResources().openRawResource(file);
//
//        byte b[] = new byte[is.available()]; // kun små filer
//        is.read(b);
//        String str = new String(b, "UTF-8");
//
//        JSONObject json = new JSONObject();
//        JSONObject jA = new JSONObject(str);
//
//        JSONArray section;
//        JSONArray category;
//        JSONObject dish;
//
//        RealmList<DishDTO> result = new RealmList<DishDTO>();
//
//        section = jA.getJSONArray("abc123");
//        for (int i = 0; i < section.length(); i++){
//            category = section.getJSONArray(0);
//            switch (i){
//                case 0: category = section.getJSONArray(1);
//                    break;
//                case 1: category = section.getJSONArray(2);
//                    break;
//                case 2: category = section.getJSONArray(3);
//                    break;
//                case 3: category = section.getJSONArray(4);
//                    break;
//            }
//            for (int j = 0; j < category.length(); j++) {
//                dish = category.getJSONObject(j);
//                String name = dish.getString("name");
//                String imagePath = dish.getString("image");
//                String deskShort = dish.getString("desk_short");
//                String deskFull = dish.getString("desk_full");
//                JSONArray ingredientList = dish.getJSONArray("Ingredients");
//                ArrayList<String[]> ingredients = new ArrayList<String[]>();
//                for (int k = 0; k < ingredientList.length(); k++){
//                    JSONObject ingredient = ingredientList.getJSONObject(k);
//                    ingredients.add(new String[]{ingredient.getString("name"), ingredient.getString("stats")});
//                }
//                result.add(new DishDTO(i, name, imagePath, deskShort, deskFull, ingredients));
//            }
//        }
//
//        RealmList<WorkoutDTO> plan = new RealmList<WorkoutDTO>();
//        JSONArray plans;
//        JSONArray passes;
//        JSONArray exercises;
//        section = jA.getJSONArray("workout");
//        category = section.getJSONArray(0);
//
//        plans = category.getJSONArray(0);
//        for (int i = 0; i < plans.length(); i++) {
//            String planName = plans.getJSONObject(i).getString("name");
//            passes = plans.getJSONObject(i).getJSONArray("pas");
//            RealmList<WorkoutPasDTO> pas = new RealmList<WorkoutPasDTO>();
//
//            for (int j = 0; j < passes.length(); j++) {
//                String pasName = passes.getJSONObject(j).getString("name");
//                pas.add(new WorkoutPasDTO());
//                exercises = passes.getJSONObject(j).getJSONArray("exercises");
//                String exName = "";
//                int sets = 0;
//                int repetitions = 0;
//
//                for (int k = 0; k < exercises.length(); k++){
//                    String exerciseName = exercises.getJSONObject(k).getString("name");
//                    RealmList<ExerciseDTO> curExercises = dao.getExercisesDTO();
//                    for (int l = 0; l < curExercises.size(); l++){
//                        if(exerciseName.equals(curExercises.get(l).getName())){
//                            exName = curExercises.get(l).getName();
//                            sets = exercises.getJSONObject(k).getInt("set");
//                            repetitions = exercises.getJSONObject(k).getInt("reps");
//
//                        }
//                    }
//                }
//                pas.get(pas.size()-1).setName(pasName);
//                pas.get(pas.size()-1).addGoals(exName, sets, repetitions);
//            }
//            plan.add(new WorkoutDTO(planName, pas));
//        }
//    }
}