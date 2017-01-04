package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.R;

/**
 * Created by Nicki on 14/11/16.
 */

public class ExerciseDAO {
    /*
    public ArrayList<ExerciseDTO> getExercises(Activity act) throws IOException, JSONException {
        ArrayList<ExerciseDTO> result = new ArrayList<ExerciseDTO>();
        InputStream is = act.getResources().openRawResource(R.raw.exercise);
        byte b[] = new byte[is.available()]; // kun små filer
        is.read(b);
        String str = new String(b, "UTF-8");
        JSONObject json = new JSONObject();
        JSONObject jA = new JSONObject(str);
        JSONArray category;
        JSONObject exer;

        category = jA.getJSONArray("Exercises");
        for (int j = 0; j < category.length(); j++) {
            exer = category.getJSONObject(j);
            String name = exer.getString("name");
            String desc = exer.getString("description");
            JSONArray setList = exer.getJSONArray("set");
            ArrayList<String[]> sets = new ArrayList<String[]>();
            for (int k = 0; k < setList.length(); k++) {
                JSONObject set = setList.getJSONObject(k);
                sets.add(new String[]{set.getString("date"), set.getString("weight"), set.getString("repetitions")});
            }
            result.add(new ExerciseDTO(name, desc, sets));
        }
        return result;
    }*/
}
