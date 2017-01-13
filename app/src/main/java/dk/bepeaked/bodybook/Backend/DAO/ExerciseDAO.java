package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;
import android.util.Log;

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
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
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
    public void newExercise(ExerciseDTO exerciseDTO) throws ExceptionNameAlreadyExist {

        RealmList<ExerciseDTO> exercises = getExercises();
            if(checkExerciseNameInPas(exerciseDTO.getName())){
                throw new ExceptionNameAlreadyExist("An exercise by the name "+ exerciseDTO.getName() + " already exist");
            }
        realm.beginTransaction();
        realm.copyToRealm(exerciseDTO);
        realm.commitTransaction();
    }

    /**
     * Gets a RealmList of all the exercises in database (in every plan, in every pas)
     * @return RealmList<ExerciseDTO>
     */
    public RealmList<ExerciseDTO> getExercises() throws IndexOutOfBoundsException, NullPointerException {
        RealmResults<ExerciseDTO> resultExercise = realm.where(ExerciseDTO.class).findAll();
        RealmList<ExerciseDTO> exercisePlans = new RealmList<>();
        exercisePlans.addAll(resultExercise.subList(0, resultExercise.size()));
        return exercisePlans;
    }

    /**
     * Gets a single exercise
     * @param id
     * @return ExerciseDTO
     */
    public ExerciseDTO getExercise(int id){
        RealmList<ExerciseDTO> exercises = getExercises();
        for(int i = 0; i < exercises.size(); i++){
            if(exercises.get(i).getID()==id){
                return exercises.get(i);
            }
        }
        return null;
    }

    /**
     * Gets a RealmList of all exercises in a specific pas
     * @param pasID
     * @return RealmList<ExerciseDTO>
     * @throws Exception if the pas doesnt exist either in the plan
     */
    public RealmList<ExerciseDTO> getExercisesInPas(int pasID) throws IndexOutOfBoundsException {

//        int position = -1;
//
//        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();
//
//        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkoutPasses();
//        for(int i = 0; i < realmPas.size(); i++){
//            if(realmPas.get(i).getName().equals(pasName)){
//                position = i;
//                break;
//            }
//        }
//
//        if(position == -1){
//            throw new Exception();
//        }else {
//
//            RealmList<ExerciseDTO> allExercises = getExercises();
//            RealmList<ExerciseDTO> pasExercises = new RealmList<ExerciseDTO>();
//
//            ArrayList<String> exerciseNamesPas = null;
//
//            for(int i = 0; i < allExercises.size(); i++){
//                String exerciseName = allExercises.get(i).getName();
//                for(int k = 0; k < exerciseNamesPas.size(); k++){
//                    String exerciseNamePas = pasExercises.get(k).getName();
//                    if(exerciseName.equals(exerciseNamePas)){
//                        pasExercises.add(allExercises.get(i));
//                    }
//                }
//
//            }
            WorkoutPasDTO pas = realm.where(WorkoutPasDTO.class).equalTo("id", pasID).findFirst();
            RealmList<ExerciseDTO> allExercises = getExercises();
            RealmList<ExerciseDTO> pasExercises = new RealmList<>();
            for(int i = 0; i<pas.getExercises().size(); i++){
                int id = pas.getExercises().get(i).getID();
                for(int j = 0; j<allExercises.size(); j++){
                    if(allExercises.get(j).getID()==id){
                        pasExercises.add(allExercises.get(j));
                    }
                }
            }

            return pasExercises;
//        }

    }

    /**
     * Updates an exercise's name everywhere
     * @param id
     * @param newName
     */
    public void updateExerciseName(int id, String newName) throws ExceptionCantDelete {
        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("id", id).findFirst();
        if(realmExercise.getImagePath2() != null){
            throw new ExceptionCantDelete("Cant delete this app's precreated exercises");
        }else {
            realm.beginTransaction();
            //Updates the name in the exercise library
            realmExercise.setName(newName);

            //Updates the name in all the passes in all the plans
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
            realm.commitTransaction();
        }


    }

    /**
     * Delete an exercise from the database
     * (This deletes it from existence)
     * @param id The exercise id
     */
    public void deleteExercise(int id) throws ExceptionCantDelete {

        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("id", id).findFirst();

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
                        if (exercises.get(j).getID()==id) {
                            pasDAO.removeExerciseFromPas(exercises.get(j).getID());
                            break;
                        }
                    }
                }
            }
            realm.commitTransaction();
        }
    }

    public boolean checkExerciseNameInPas(String name) throws ExceptionNameAlreadyExist {
        boolean exist = false;
        WorkoutDAO workoutDAO = new WorkoutDAO();
        RealmList<WorkoutDTO> planer = workoutDAO.getPlans();
        RealmList<WorkoutPasDTO> pas;
        RealmList<ExerciseGoals> goals;
        for (int i = 0; i < planer.size(); i++) {
            pas = planer.get(i).getWorkoutPasses();
            for (int l = 0; l < pas.size(); l++) {
                goals = pas.get(l).getExercises();
                for(int j = 0; j<goals.size(); j++) {
                    if (goals.get(j).getName().equals(name)) {
                        exist = true;
                    }

                }
            }
        }
        return exist;
    }

}
