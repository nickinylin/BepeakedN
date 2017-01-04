package dk.bepeaked.bodybook.Backend.DAO;

import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by sebho on 14-11-2016.
 */

public class WorkoutDAO {

    Realm realm = Realm.getDefaultInstance();
    public void newPlan(WorkoutDTO workoutDTO){
        realm.beginTransaction();
        WorkoutDTO realmPlan = realm.copyToRealm(workoutDTO);
        realm.commitTransaction();
    }

    public RealmList<WorkoutDTO> getPlans(){
        RealmResults<WorkoutDTO> resultPlans = realm.where(WorkoutDTO.class).findAll();
        RealmList<WorkoutDTO> workoutPlans = new RealmList<WorkoutDTO>();
        workoutPlans.addAll(resultPlans.subList(0, resultPlans.size()));
        return workoutPlans;
    }

    public void updatePlan(String oldname, WorkoutDTO newWorkoutDTO){

        WorkoutDTO plan = realm.where(WorkoutDTO.class).equalTo("Name", oldname).findFirst();

        realm.commitTransaction();
        plan = newWorkoutDTO;
        realm.commitTransaction();
    }

}
