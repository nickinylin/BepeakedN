package dk.bepeaked.bodybook.Backend.DAO;

import android.util.Log;

import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
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
    WorkoutPasDAO workoutPasDAO = new WorkoutPasDAO();

    /**
     * Adds a new workoutplan
     * @param workoutDTO (String name, RealmList<WorkoutPasDTO>)
     */
    public void newPlan(WorkoutDTO workoutDTO) throws ExceptionNameAlreadyExist {
        RealmList<WorkoutDTO> plans = getPlans();
        for(int i = 0; i<plans.size(); i++){
            if(plans.get(i).getName().equals(workoutDTO.getName())){
                throw new ExceptionNameAlreadyExist("A plan by the name "+workoutDTO.getName() +" already exist");
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
    public RealmList<WorkoutDTO> getPlans(){
        RealmResults<WorkoutDTO> resultPlans = realm.where(WorkoutDTO.class).findAll();
        RealmList<WorkoutDTO> workoutPlans = new RealmList<WorkoutDTO>();
        workoutPlans.addAll(resultPlans.subList(0, resultPlans.size()));
        return workoutPlans;
    }

    /**
     * Updates a plan name
     * @param oldname
     * @param newname
     */
    public void updatePlanName(String oldname, String newname) throws ExceptionPasDoesntExist, ExceptionNameAlreadyExist {

        WorkoutDTO plan = realm.where(WorkoutDTO.class).equalTo("name", oldname).findFirst();
        WorkoutDTO newPlan = new WorkoutDTO();
        newPlan.setName(newname);
        newPlan.setWorkoutPas(plan.getWorkoutPasses());

        RealmList<WorkoutDTO> planer = new RealmList<>();
        planer = getPlans();

        int position = -1;
        for(int i = 0; i<planer.size(); i++){
            if(planer.get(i).getName().equals(newname)){
                throw new ExceptionNameAlreadyExist("A plan by the name "+ newname + " already exist");
            }else {
                if (planer.get(i).getName().equals(oldname)) {
                    position = i;
                    break;
                }
            }
        }
        realm.commitTransaction();
        cleanPlan(oldname);
        planer.set(position, newPlan);
        realm.commitTransaction();
    }

    /**
     * Deletes a plan
     * @param name The name of the plan
     */
    public void deletePlan(String name) throws ExceptionPasDoesntExist {

        WorkoutDTO plan = realm.where(WorkoutDTO.class).equalTo("name", name).findFirst();

        realm.beginTransaction();
        cleanPlan(name);
        plan.deleteFromRealm();
        realm.commitTransaction();
    }

    public void cleanPlan(String planName) throws ExceptionPasDoesntExist {
        WorkoutDTO workoutDTO = new WorkoutDTO();
        workoutDTO = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();
        for(int i = 0; i < workoutDTO.getWorkoutPasses().size(); i++){
            workoutPasDAO.deletePas(planName, workoutDTO.getWorkoutPasses().get(i).getName());

        }
    }
}
