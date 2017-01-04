package dk.bepeaked.bodybook.Backend.DAO;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPassDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDAO {

    Realm realm = Realm.getDefaultInstance();
    public void newPas(WorkoutPassDTO workoutPassDTO){
        realm.beginTransaction();
        WorkoutPassDTO realmPas = realm.copyToRealm(workoutPassDTO);
        realm.commitTransaction();
    }

    public RealmList<WorkoutPassDTO> getPasses(){
        RealmResults<WorkoutPassDTO> resultPasses = realm.where(WorkoutPassDTO.class).findAll();
        RealmList<WorkoutPassDTO>workoutPasses = new RealmList<WorkoutPassDTO>();
        workoutPasses.addAll(resultPasses.subList(0, resultPasses.size()));
        return workoutPasses;
    }

    public void updatePassName(String oldname, String newname){

        WorkoutPassDTO pass = realm.where(WorkoutPassDTO.class).equalTo("Name", oldname).findFirst();

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
