package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import dk.bepeaked.bodybook.R;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Nicki on 14/11/16.
 */

public class ExerciseDAO {

    Realm realm = Realm.getDefaultInstance();

    /**
     * Add's a new exercise
     * @param exerciseDTO (String Name, String Description1, String Description2, String Imagepath1, String imagepath2, RealmList<SetDTO></SetDTO>)
     */
    public void newExercise(ExerciseDTO exerciseDTO) {
        realm.beginTransaction();
        ExerciseDTO realmExerciseDTO = realm.copyToRealm(exerciseDTO);
        realm.commitTransaction();
    }

    /**
     * Gets a RealmList of all the exercises in database (in every plan, in every pas)
     * @return RealmList<ExerciseDTO>
     */
    public RealmList<ExerciseDTO> getExercisesDTO() {
        RealmResults<ExerciseDTO> resultExercise = realm.where(ExerciseDTO.class).findAll();
        RealmList<ExerciseDTO> exercisePlans = new RealmList<ExerciseDTO>();
        exercisePlans.addAll(resultExercise.subList(0, resultExercise.size()));
        return exercisePlans;
    }

    /**
     * Updates an exercise's name
     * @param oldname
     * @param newName
     */
    public void updateExerciseName(String oldname, String newName){
        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("name", oldname).findFirst();

        realm.beginTransaction();
        realmExercise.setName(newName);
        realm.commitTransaction();
    }

    /**
     * Delete an exercise from the database
     * @param name
     */
    public void deleteExercise(String name){

        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("name", name).findFirst();

        realm.beginTransaction();
        realmExercise.deleteFromRealm();
        realm.commitTransaction();
    }

    /**
     * Add a set to an exercise
     * @param exerciseName The exercise which you want to add the set to
     * @param newSet The new set you want to add
     */
    public void addSet(String exerciseName, SetDTO newSet){

        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("name", exerciseName).findFirst();

        realm.beginTransaction();
        realmExercise.getSet().add(newSet);
        realm.commitTransaction();
    }

    public void deleteSet(String exerciseName, SetDTO setDeleted){

        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("name", exerciseName).findFirst();

        realm.beginTransaction();
        realmExercise.deleteFromRealm();
        realm.commitTransaction();
    }
}
