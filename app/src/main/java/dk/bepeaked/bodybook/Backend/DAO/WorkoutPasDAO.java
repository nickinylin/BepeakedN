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
    public void newPas(WorkoutDTO workoutDTO){
        realm.beginTransaction();
        WorkoutDTO realmPas = realm.copyToRealm(workoutDTO);
        realm.commitTransaction();
    }

    public RealmList<WorkoutDTO> getPasses(){
        RealmResults<WorkoutDTO> resultPasses = realm.where(WorkoutDTO.class).findAll();
        RealmList<WorkoutDTO>workoutPasses = new RealmList<WorkoutDTO>();
        workoutPasses.addAll(resultPasses.subList(0, resultPasses.size()));
        return workoutPasses;
    }

    public void updatePassName(String oldname, String newname){

        WorkoutDTO pass = realm.where(WorkoutDTO.class).equalTo("Name", oldname).findFirst();

        realm.commitTransaction();
        pass.setName(newname);
        realm.commitTransaction();
    }

    public void updatePassExercise(String name, RealmList<ExerciseDTO> newExerciseList){

        WorkoutPasDTO pass = realm.where(WorkoutPasDTO.class).equalTo("Name", name).findFirst();

        realm.beginTransaction();
        pass.setExercises(newExerciseList);
        realm.commitTransaction();
    }
}
