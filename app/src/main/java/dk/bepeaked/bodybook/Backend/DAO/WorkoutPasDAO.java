package dk.bepeaked.bodybook.Backend.DAO;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDAO {

    Realm realm = Realm.getDefaultInstance();

    public void newPas(String planName, WorkoutPasDTO workoutPasDTO){

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        realm.beginTransaction();
        realmPlan.getWorkoutPasses().add(workoutPasDTO);
        realm.commitTransaction();
    }


    public RealmList<WorkoutPasDTO> getPasses(String planName){

        WorkoutDTO realmPas = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO>workoutPasses = realmPas.getWorkoutPasses();
        return workoutPasses;
    }


    public void updatePasName(String planName, String oldPasName, String newPasName) throws Exception {

        int position = -1;

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkoutPasses();
        for(int i = 0; i < realmPas.size(); i++){
            if(realmPas.get(i).getName().equals(oldPasName)){
                position = i;
                break;
            }
        }

        if(position == -1){
            throw new Exception();
        }else {
            realm.beginTransaction();
            realmPlan.getWorkoutPasses().get(position).setName(newPasName);
            realm.commitTransaction();
        }
    }


    public void deletePas(String planName, String pasName) throws Exception {

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
            realm.beginTransaction();
            realmPlan.getWorkoutPasses().remove(position);
            realm.commitTransaction();
        }
    }

     public void addExerciseToPas(String planName, String pasName, String exerciseName) throws Exception {

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
             realm.beginTransaction();
             realmPlan.getWorkoutPasses().get(position).getExercises().add(exerciseName);
             realm.commitTransaction();
         }
     }

    public void removeExerciseFromPas(String planName, String pasName, ExerciseDTO exerciseToBeDeleted) throws Exception {

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
            realm.beginTransaction();
            realmPlan.getWorkoutPasses().get(position).getExercises().remove(exerciseToBeDeleted);
            realm.commitTransaction();
        }
    }
}
