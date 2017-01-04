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

    public void updatePas(String oldname, WorkoutPasDTO newWorkoutPassDTO){

        WorkoutPasDTO pas = realm.where(WorkoutPasDTO.class).equalTo("Name", oldname).findFirst();

        realm.commitTransaction();
        pas = newWorkoutPassDTO;
        realm.commitTransaction();
    }

}
