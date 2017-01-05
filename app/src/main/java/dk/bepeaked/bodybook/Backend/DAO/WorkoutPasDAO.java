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

    public void updatePasName(String oldname, String newName){

        WorkoutPasDTO realmPas = realm.where(WorkoutPasDTO.class).equalTo("Name", oldname).findFirst();

        realm.commitTransaction();
        realmPas.setName(newName);
        realm.commitTransaction();
    }

    public void deletePas(String name){

        WorkoutPasDTO realmPas = realm.where(WorkoutPasDTO.class).equalTo("name", name).findFirst();

        realm.beginTransaction();
        realmPas.deleteFromRealm();
        realm.commitTransaction();
    }

     public void addNewExercise(String pasName, ExerciseDTO newExercise){

         WorkoutPasDTO realmPas = realm.where(WorkoutPasDTO.class).equalTo("name", pasName).findFirst();

         realm.beginTransaction();
         realmPas.getExercises().add(newExercise);
         realm.commitTransaction();
     }

    public void deleteExercise(String pasName, ExerciseDTO exerciseToBeDeleted){

         WorkoutPasDTO realmPas = realm.where(WorkoutPasDTO.class).equalTo("name", pasName).findFirst();

        realm.beginTransaction();
        realmPas.getExercises().remove(exerciseToBeDeleted);
    }
}
