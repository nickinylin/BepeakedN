package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseGoals;
import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionCantDelete;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
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
     * Add's a new exercise to the exercise library
     * @param exerciseDTO (String Name, String Description1, String Description2, String Imagepath1, String imagepath2, ArrayList<String>)
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
    public RealmList<ExerciseDTO> getExercises() {
        RealmResults<ExerciseDTO> resultExercise = realm.where(ExerciseDTO.class).findAll();
        RealmList<ExerciseDTO> exercisePlans = new RealmList<ExerciseDTO>();
        exercisePlans.addAll(resultExercise.subList(0, resultExercise.size()));
        return exercisePlans;
    }

    /**
     * Gets a single exercise
     * @param name
     * @return ExerciseDTO
     */
    public ExerciseDTO getExercise(String name){
        RealmList<ExerciseDTO> exercises = getExercises();
        for(int i = 0; i < exercises.size(); i++){
            if(exercises.get(i).getName().equals(name)){
                return exercises.get(i);
            }
        }
        return null;
    }

    /**
     * Gets a RealmList of all exercises in a specific pas, in a specific plan
     * @param planName
     * @param pasName
     * @return RealmList<ExerciseDTO>
     * @throws Exception if the pas doesnt exist either in the plan
     */
    public RealmList<ExerciseDTO> getExercisesInPas(String planName, String pasName) throws Exception {

        int position = -1;

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkoutPasses();
        for(int i = 0; i < realmPas.size(); i++){
            if(realmPas.get(i).getName().equals(pasName)){
                position = i;
                break;
            }
        }

        if(position == -1){
            throw new Exception();
        }else {

            RealmList<ExerciseDTO> allExercises = getExercises();
            RealmList<ExerciseDTO> pasExercises = new RealmList<ExerciseDTO>();

            ArrayList<String> exerciseNamesPas = null;

            for(int i = 0; i < allExercises.size(); i++){
                String exerciseName = allExercises.get(i).getName();
                for(int k = 0; k < exerciseNamesPas.size(); k++){
                    String exerciseNamePas = pasExercises.get(k).getName();
                    if(exerciseName.equals(exerciseNamePas)){
                        pasExercises.add(allExercises.get(i));
                    }
                }

            }

            return pasExercises;
        }

    }

//    /**
//     * Updates an exercise's name everywhere
//     * @param oldname
//     * @param newName
//     */
//    public void updateExerciseName(String oldname, String newName){
//        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("name", oldname).findFirst();
//
//        realm.beginTransaction();
//        //Updates the name in the exercise library
//        realmExercise.setName(newName);
//
//        //Updates the name in all the passes in all the plans
//        WorkoutDAO planDAO = new WorkoutDAO();
//        RealmList<WorkoutDTO> planer;
//        planer = planDAO.getPlans();
//
//        for(int i = 0; i < planer.size(); i++){
//            RealmList<WorkoutPasDTO> pas = planer.get(i).getWorkoutPasses();
//            for(int k = 0; k < pas.size(); i++){
//                RealmList<ExerciseGoals> exercises = pas.get(k).getExercises();
//                for(int j = 0; j < exercises.size(); j++){
//                    if(exercises.get(j).getName().equals(oldname)){
//                        exercises.get(j).setName(newName);
//                        break;
//                    }
//                }
//            }
//        }
//        realm.commitTransaction();
//
//
//    }

    /**
     * Delete an exercise from the database
     * (This deletes it from existence)
     * @param name The exercise name
     */
    public void deleteExercise(String name) throws ExceptionPasDoesntExist, ExceptionCantDelete {

        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("name", name).findFirst();

        if(realmExercise.getImagePath2() != null){
            throw new ExceptionCantDelete("Cant delete this app's exercises");
        }else {
            realm.beginTransaction();
            //Deletes the exercises from the exercises library
            realmExercise.deleteFromRealm();

            //Deletes the exercises from all the passes in all the plans
            WorkoutDAO planDAO = new WorkoutDAO();
            WorkoutPasDAO pasDAO = new WorkoutPasDAO();
            RealmList<WorkoutDTO> planer;
            planer = planDAO.getPlans();

            for (int i = 0; i < planer.size(); i++) {
                RealmList<WorkoutPasDTO> pas = planer.get(i).getWorkoutPasses();
                for (int k = 0; k < pas.size(); k++) {
                    RealmList<ExerciseGoals> exercises = pas.get(k).getExercises();
                    for (int j = 0; j < exercises.size(); j++) {
                        if (exercises.get(j).getName().equals(name)) {
                            pasDAO.removeExerciseFromPas(planer.get(i).getName(), pas.get(k).getName(), exercises.get(j).getName());
                            break;
                        }
                    }
                }
            }
            realm.commitTransaction();
        }
    }

}
