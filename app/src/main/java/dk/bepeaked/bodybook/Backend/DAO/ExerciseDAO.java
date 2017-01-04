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
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Nicki on 14/11/16.
 */

public class ExerciseDAO {

    Realm realm = Realm.getDefaultInstance();

    public void newExercise(ExerciseDTO exerciseDTO) {
        realm.beginTransaction();
        ExerciseDTO realmExerciseDTO = realm.copyToRealm(exerciseDTO);
        realm.commitTransaction();
    }

    public RealmList<ExerciseDTO> getExercisesDTO() {
        RealmResults<ExerciseDTO> resultExercise = realm.where(ExerciseDTO.class).findAll();
        RealmList<ExerciseDTO> exercisePlans = new RealmList<ExerciseDTO>();
        exercisePlans.addAll(resultExercise.subList(0, resultExercise.size()));
        return exercisePlans;
    }

    public void updateExercise(String oldname, ExerciseDTO newExerciseDTO){
        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("Name", oldname).findFirst();

        realm.beginTransaction();
        realmExercise = newExerciseDTO;
        realm.commitTransaction();
    }
}
