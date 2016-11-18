package dk.bepeaked.bodybook.Backend.DAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import dk.bepeaked.bodybook.R;

/**
 * Created by sebho on 14-11-2016.
 */

public class DietDAO {
    private void createJSON() throws IOException, JSONException {
        JSONObject json = new JSONObject();



        JSONArray jA = json.getJSONArray(String.valueOf(R.raw.dish));

//        Iterator<String> iterator =
        for (int i = 0; i < jA.length(); i++){
            jA.get(i);
        }
    }
}
