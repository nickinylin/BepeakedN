package dk.bepeaked.bodybook.Backend.DAO;

import android.util.Log;

import org.apache.commons.lang3.ObjectUtils;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseGoals;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNullPointer;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDAO {

    Realm realm = Realm.getDefaultInstance();

    /**
     * Adds a new pas to a plan
     *
     * @param planName      The plan you wish to add the pas to
     * @param workoutPasDTO the new workoutpas
     */
    public void newPas(String planName, WorkoutPasDTO workoutPasDTO) throws ExceptionNameAlreadyExist {

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO> passes = realmPlan.getWorkoutPasses();
        for(int i = 0; i<passes.size(); i++){
            if(passes.get(i).getName().equals(workoutPasDTO.getName())){
                throw new ExceptionNameAlreadyExist("A pas with the name "+ workoutPasDTO.getName() +" already exist");
            }
        }
        realm.beginTransaction();
        Log.d("Nicki", "WorkoutPasDAO begintra: ");
        realmPlan.getWorkoutPasses().add(workoutPasDTO);
        realm.commitTransaction();
    }

    /**
     * Gets a list of all the passes created in a plan
     *
     * @param planName
     * @return RealmList<WorkoutPasDTO>
     */
    public RealmList<WorkoutPasDTO> getPasses(String planName) {

        WorkoutDTO realmPas = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        return realmPas.getWorkoutPasses();

    }

    /**
     * Updates a pas name
     *
     * @param planName   The plan it's in
     * @param oldPasName
     * @param newPasName
     * @throws Exception if it doesnt exist in the plan
     */
    public void updatePasName(String planName, String oldPasName, String newPasName) throws ExceptionPasDoesntExist, ExceptionNameAlreadyExist {

        int position = -1;

        WorkoutDTO oldRealmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO> realmPas = oldRealmPlan.getWorkoutPasses();
        for (int i = 0; i < realmPas.size(); i++) {
            if(realmPas.get(i).getName().equals(newPasName)){
                throw new ExceptionNameAlreadyExist("A pas by the name "+newPasName+" already exist");
            }
            if (realmPas.get(i).getName().equals(oldPasName)) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            throw new ExceptionPasDoesntExist("The pas doesnt exist");
        } else {
            WorkoutPasDTO updatedRealmPas = new WorkoutPasDTO();
            RealmList<ExerciseGoals> exercises = oldRealmPlan.getWorkoutPasses().get(position).getExercises();

            updatedRealmPas.setName(newPasName);
            for(int i = 0; i < exercises.size(); i++){
                updatedRealmPas.setExerciseGoal(oldRealmPlan.getWorkoutPasses().get(i).getExercises());
            }
            WorkoutPasDTO oldRealmPas = realm.where(WorkoutPasDTO.class).equalTo("name", oldPasName).findFirst();
            RealmList<ExerciseGoals> goals = oldRealmPas.getExercises();

            realm.beginTransaction();
            oldRealmPlan.getWorkoutPasses().set(position, updatedRealmPas);
            for(int i = 0; i < goals.size(); i++){
                goals.get(i).deleteFromRealm();
            }
            oldRealmPas.deleteFromRealm();
            realm.commitTransaction();
        }
    }

    /**
     * Deletes a pas from a plan
     *
     * @param planName the plan its in
     * @param pasName  the name of the pas you want to delete
     * @throws Exception if it doesn't exist in the plan
     */
    public void deletePas(String planName, String pasName) throws ExceptionPasDoesntExist {

        int position = -1;

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkoutPasses();
        for (int i = 0; i < realmPas.size(); i++) {
            if (realmPas.get(i).getName().equals(pasName)) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            throw new ExceptionPasDoesntExist("The pas "+pasName+" in " + planName + " doesnt exist");
        } else {
            WorkoutPasDTO pas = realm.where(WorkoutPasDTO.class).equalTo("name", pasName).findFirst();
            RealmList<ExerciseGoals> goals = pas.getExercises();

            realm.beginTransaction();
            for(int i = 0; i < goals.size(); i++){
                goals.get(i).deleteFromRealm();
            }
            realmPlan.getWorkoutPasses().remove(position);
            pas.deleteFromRealm();
            realm.commitTransaction();
        }
    }

    /**
     * Adds an exercise to a pas
     * @param planName     the plan the pas is in
     * @param pasName      the name of the pas
     * @param exerciseName the name of the exercise
     * @param sets         the amount of sets of said exercise
     * @param reps         the amount of reps of said exercise
     * @throws Exception if the pas doesn't exist in the plan
     */
    public void addExerciseToPas(String planName, String pasName, String exerciseName, int sets, int reps) throws ExceptionPasDoesntExist {

        int position = -1;

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkoutPasses();
        for (int i = 0; i < realmPas.size(); i++) {
            if (realmPas.get(i).getName().equals(pasName)) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            throw new ExceptionPasDoesntExist("The pas "+pasName+" in " + planName + " doesnt exist");
        } else {
            realm.beginTransaction();
            ExerciseGoals newExercise = new ExerciseGoals(exerciseName, sets, reps);
            realmPlan.getWorkoutPasses().get(position).getExercises().add(newExercise);
            realm.commitTransaction();
        }
    }

    /**
     * Removes an exercise from a pas
     *
     * @param planName     the plan the pas is in
     * @param pasName      the pas the exercise is in
     * @param exerciseName the name of the exercise
     * @throws Exception if the pas doesn't exist in the plan
     */
    public void removeExerciseFromPas(String planName, String pasName, String exerciseName) throws ExceptionPasDoesntExist {

        int position = -1;

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkoutPasses();
        for (int i = 0; i < realmPas.size(); i++) {
            if (realmPas.get(i).getName().equals(pasName)) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            throw new ExceptionPasDoesntExist("The pas "+pasName+" in " + planName + " doesnt exist");
        } else {
            realm.beginTransaction();
            RealmList<ExerciseGoals> exercises = realmPlan.getWorkoutPasses().get(position).getExercises();
            for (int i = 0; i < exercises.size(); i++) {
                if (exercises.get(i).getName().equals(exerciseName)) {
                    realmPlan.getWorkoutPasses().get(position).getExercises().remove(i);
                    exercises.deleteFromRealm(i);
                    break;
                }
            }
            realm.commitTransaction();
        }
    }
}
