package dk.bepeaked.bodybook.Backend.DAO;

import android.util.Log;

import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.internal.Context;

/**
 * Created by sebho on 14-11-2016.
 */

public class WorkoutDAO {

    public WorkoutDAO (){
    }

    Realm realm = Realm.getDefaultInstance();
    WorkoutPasDAO workoutPasDAO = new WorkoutPasDAO();
    Singleton singleton  = Singleton.singleton;

    /**
     * Adds a new workoutplan
     * @param workoutDTO (String name, RealmList<WorkoutPasDTO>)
     */
    public void newPlan(WorkoutDTO workoutDTO) throws ExceptionNameAlreadyExist {
        RealmList<WorkoutDTO> plans = getPlans();
        for(int i = 0; i<plans.size(); i++){
            if(plans.get(i).getName().equals(workoutDTO.getName())){
                throw new ExceptionNameAlreadyExist(singleton.getString(R.string.same_name_plan)+workoutDTO.getName() +singleton.getString(R.string.already_exists));
            }
        }
        realm.beginTransaction();
        WorkoutDTO realmPlan = realm.copyToRealm(workoutDTO);
        realm.commitTransaction();
    }

    /**
     * Gets a list of all the plans
     * @return RealmList<WorkoutDTO>
     */
    public RealmList<WorkoutDTO> getPlans()throws IndexOutOfBoundsException{
        RealmResults<WorkoutDTO> resultPlans = realm.where(WorkoutDTO.class).findAll();
        RealmList<WorkoutDTO> workoutPlans = new RealmList<WorkoutDTO>();
        workoutPlans.addAll(resultPlans.subList(0, resultPlans.size()));
        return workoutPlans;
    }

    /**
     * Updates a plan name
     * @param id
     * @param newname
     */
    public void updatePlanName(int id, String newname) throws ExceptionPasDoesntExist, ExceptionNameAlreadyExist {

        RealmList<WorkoutDTO> planer = new RealmList<>();
        planer = getPlans();

        int position = -1;
        for(int i = 0; i<planer.size(); i++){
            if(planer.get(i).getName().equals(newname)){
                throw new ExceptionNameAlreadyExist(singleton.getString(R.string.same_name_plan)+ newname + singleton.getString(R.string.already_exists));
            }
        }

        realm.beginTransaction();
        WorkoutDTO plan = realm.where(WorkoutDTO.class).equalTo("id", id).findFirst();
        plan.setName(newname);
        realm.commitTransaction();
    }

    /**
     * Deletes a plan
     * @param id The id of the plan
     */
    public void deletePlan(int id) throws ExceptionPasDoesntExist {

        WorkoutDTO plan = realm.where(WorkoutDTO.class).equalTo("id", id).findFirst();

        cleanPlan(id);
        realm.beginTransaction();
        plan.deleteFromRealm();
        realm.commitTransaction();
    }

    public void cleanPlan(int id) throws ExceptionPasDoesntExist {
        WorkoutDTO workoutDTO = workoutDTO = realm.where(WorkoutDTO.class).equalTo("id", id).findFirst();

        for(int i = 0; i < workoutDTO.getWorkoutPasses().size(); i++){
            workoutPasDAO.deletePas(workoutDTO.getWorkoutPasses().get(i).getID());

        }
    }
}
