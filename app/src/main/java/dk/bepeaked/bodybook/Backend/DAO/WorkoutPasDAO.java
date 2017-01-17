package dk.bepeaked.bodybook.Backend.DAO;

import android.util.Log;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseGoals;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import dk.bepeaked.bodybook.Backend.Singleton;
import dk.bepeaked.bodybook.R;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDAO {

    Realm realm = Realm.getDefaultInstance();
Singleton singleton = Singleton.singleton;
    /**
     * Adds a new pas to a plan
     *
     * @param planID        The plan you wish to add the pas to
     * @param workoutPasDTO the new workoutpas
     */
    public void newPas(int planID, WorkoutPasDTO workoutPasDTO) throws ExceptionNameAlreadyExist {

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("id", planID).findFirst();


        realm.beginTransaction();
        realmPlan.getWorkoutPasses().add(workoutPasDTO);
        realm.commitTransaction();
//        }
    }

    /**
     * Gets a list of all the passes created in a plan
     *
     * @param planID
     * @return RealmList<WorkoutPasDTO>
     */
    public RealmList<WorkoutPasDTO> getPasses(int planID) throws IndexOutOfBoundsException, NullPointerException {

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("id", planID).findFirst();

        return realmPlan.getWorkoutPasses();
    }

    /**
     * Updates a pas name
     *
     * @param pasID
     * @param newPasName
     * @throws Exception if it doesnt exist in the plan
     */
    public void updatePasName(int pasID, String newPasName) throws ExceptionNameAlreadyExist {

        WorkoutPasDTO pas = realm.where(WorkoutPasDTO.class).equalTo("id", pasID).findFirst();


        realm.beginTransaction();
        pas.setName(newPasName);
        realm.commitTransaction();

    }

    /**
     * Deletes a pas from a plan
     *
     * @param pasId  the id of the pas you want to delete
     * @throws Exception if it doesn't exist in the plan
     */
    public void deletePas(int pasId) throws ExceptionPasDoesntExist {
        WorkoutPasDTO pas = realm.where(WorkoutPasDTO.class).equalTo("id", pasId).findFirst();
        RealmList<ExerciseGoals> goals = new RealmList<ExerciseGoals>();
        try {
            goals = pas.getExercises();
        } catch (NullPointerException e) {

        }

        realm.beginTransaction();
        for (int i = 0; i < goals.size(); i++) {
            goals.get(i).deleteFromRealm();
        }
        pas.deleteFromRealm();
        realm.commitTransaction();
    }

    /**
     * Adds an exercise to a pas
     * @param pasID      the name of the pas
     * @param exerciseName the name of the exercise
     * @param sets         the amount of sets of said exercise
     * @param reps         the amount of reps of said exercise
     * @throws Exception if the pas doesn't exist in the plan
     */
    public void
    addExerciseToPas(int pasID, String exerciseName, int sets, int reps) throws ExceptionNameAlreadyExist {

        int id;

        WorkoutPasDTO realmPas = realm.where(WorkoutPasDTO.class).equalTo("id", pasID).findFirst();
        try {
            ExerciseGoals goal = realm.where(ExerciseGoals.class).findAll().last();
            id = goal.getID() + 1;
            for (int i = 0; i < realmPas.getExercises().size(); i++) {
                if (realmPas.getExercises().get(i).getName().equals(exerciseName)) {
                    throw new ExceptionNameAlreadyExist(singleton.getString(R.string.the_exercise) + exerciseName + singleton.getString(R.string.already_exists_in_pas));
                }
            }
        }catch (IndexOutOfBoundsException e){
            id = 1;
        }catch (NullPointerException e){
            id = 1;
        }

        ExerciseGoals newExercise = new ExerciseGoals(id, exerciseName, sets, reps);

        realm.beginTransaction();
        realmPas.getExercises().add(newExercise);
        realm.commitTransaction();
    }

    /**
     * Removes an exercise from a pas
     *
     * @param exerciseGoalID     the plan the pas is in
     * @throws Exception if the pas doesn't exist in the plan
     */
    public void removeExerciseFromPas(int exerciseGoalID) {


        realm.beginTransaction();
        ExerciseGoals goal = realm.where(ExerciseGoals.class).equalTo("id", exerciseGoalID).findFirst();
        goal.deleteFromRealm();
        realm.commitTransaction();

    }

    public RealmList<WorkoutPasDTO> getAllPasses() throws IndexOutOfBoundsException{
        RealmResults<WorkoutPasDTO> passes = realm.where(WorkoutPasDTO.class).findAll();
        RealmList<WorkoutPasDTO> listPasses = new RealmList<>();
        listPasses.addAll(passes.subList(0, passes.size()));
        return listPasses;
    }

    public WorkoutPasDTO getPas(int id){
        WorkoutPasDTO pas = realm.where(WorkoutPasDTO.class).equalTo("id", id).findFirst();
        return pas;
    }

}
