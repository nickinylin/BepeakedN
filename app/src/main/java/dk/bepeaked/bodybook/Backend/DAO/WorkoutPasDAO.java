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

    public void newPas(WorkoutPasDTO workoutPasDTO){
        realm.beginTransaction();
        WorkoutPasDTO realmPas = realm.copyToRealm(workoutPasDTO);
        realm.commitTransaction();
    }

    public RealmList<WorkoutPasDTO> getPasses(){
        RealmResults<WorkoutPasDTO> resultPaser = realm.where(WorkoutPasDTO.class).findAll();
        RealmList<WorkoutPasDTO>workoutPasses = new RealmList<WorkoutPasDTO>();
        workoutPasses.addAll(resultPaser.subList(0, resultPaser.size()));
        return workoutPasses;
    }

    public void updatePasName(String planName, String oldPasName, String newPasName) throws Exception {


        int position = -1;

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkouts();
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
            realmPlan.getWorkouts().get(position).setName(newPasName);
            realm.commitTransaction();
        }
    }

    public void deletePas(String planName, String pasName) throws Exception {

        int position = -1;

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkouts();
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
            realmPlan.getWorkouts().remove(position);
            realm.commitTransaction();
        }
    }

     public void addNewExercise(String planName, String pasName, ExerciseDTO newExercise) throws Exception {

         int position = -1;

         WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

         RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkouts();
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
             realmPlan.getWorkouts().get(position).getExercises().add(newExercise);
             realm.commitTransaction();
         }
     }

    public void deleteExercise(String planName, String pasName, ExerciseDTO exerciseToBeDeleted) throws Exception {

        int position = -1;

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkouts();
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
            realmPlan.getWorkouts().get(position).getExercises().remove(exerciseToBeDeleted);
            realm.commitTransaction();
        }
    }
}
