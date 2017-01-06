package dk.bepeaked.bodybook.Backend.DAO;

import android.util.Log;

import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.internal.Context;

/**
 * Created by sebho on 14-11-2016.
 */

public class WorkoutDAO {

    public WorkoutDAO (){
        Log.d("Nicki", "path: " + realm.getPath());
    }

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

    public void updatePlanName(String oldname, String newname){

        WorkoutDTO plan = realm.where(WorkoutDTO.class).equalTo("name", oldname).findFirst();

        realm.commitTransaction();
        plan.setName(newname);
        realm.commitTransaction();
    }

    public void deletePlan(String name){

        WorkoutDTO plan = realm.where(WorkoutDTO.class).equalTo("name", name).findFirst();

        realm.beginTransaction();
        plan.deleteFromRealm();
        realm.commitTransaction();
    }
}
