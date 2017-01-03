package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import dk.bepeaked.bodybook.R;

/**
 * Created by sebho on 14-11-2016.
 */

public class WorkoutDAO {
    public void getPlans(){

    }
    public void createPlan(String name, Context ct) throws JSONException, IOException {
        JSONObject plan = new JSONObject();
        plan.put("name", name);
        JSONArray pas = new JSONArray();
        plan.put("pas", pas);

        FileWriter file = new FileWriter(ct.getFilesDir().getAbsolutePath() + File.separator + "/userdata/plans");

        Log.d("sebby", "createPlan: " + ct.getFilesDir().getAbsolutePath());
        file.write(plan.toString());

    }

    public void insertPas(String planName, String pasName, Activity act) throws JSONException, IOException {
        InputStream is = act.getResources().openRawResource(R.raw.dish);

        byte b[] = new byte[is.available()];
        is.read(b);
        String str = new String(b, "UTF-8");

        JSONObject plan = new JSONObject(str);

        JSONArray pas = plan.getJSONArray("pas");
        pas.put(pasName);

    }
}
