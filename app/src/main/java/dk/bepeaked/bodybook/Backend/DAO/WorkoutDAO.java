package dk.bepeaked.bodybook.Backend.DAO;

import dk.bepeaked.bodybook.Backend.DTO.WorkoutPassDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by sebho on 14-11-2016.
 */

public class WorkoutDAO {

    Realm realm = Realm.getDefaultInstance();
    public void newPlan(WorkoutPassDTO workoutPassDTO){
        realm.beginTransaction();
        WorkoutPassDTO realmPlan = realm.copyToRealm(workoutPassDTO);
        realm.commitTransaction();
    }

    public RealmList<WorkoutPassDTO> getPlans(){
        RealmResults<WorkoutPassDTO> resultPlans = realm.where(WorkoutPassDTO.class).findAll();
        RealmList<WorkoutPassDTO> workoutPlans = new RealmList<WorkoutPassDTO>();
        workoutPlans.addAll(resultPlans.subList(0, resultPlans.size()));
        return workoutPlans;
    }

    public void updatePlanName(String oldname, String newname){

        WorkoutPassDTO plan = realm.where(WorkoutPassDTO.class).equalTo("Name", oldname).findFirst();

        realm.commitTransaction();
        plan.setName(newname);
        realm.commitTransaction();
    }

    public void updatePlanPas(String name, RealmList<WorkoutPasDTO> newWorkoutPasList){

        WorkoutPassDTO plan = realm.where(WorkoutPassDTO.class).equalTo("Name", name).findFirst();

        realm.beginTransaction();
        plan.setWorkouts(newWorkoutPasList);
        realm.commitTransaction();
    }
}
