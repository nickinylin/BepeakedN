package dk.bepeaked.bodybook.Backend.DAO;

import android.util.Log;

import org.apache.commons.lang3.ObjectUtils;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseGoals;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNullPointer;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDAO {

    Realm realm = Realm.getDefaultInstance();

    /**
     * Adds a new pas to a plan
     *
     * @param planID        The plan you wish to add the pas to
     * @param workoutPasDTO the new workoutpas
     */
    public void newPas(int planID, WorkoutPasDTO workoutPasDTO) throws ExceptionNameAlreadyExist {

        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("id", planID).findFirst();


//        if(!checkName(workoutPasDTO.getName())){
//            throw new ExceptionNameAlreadyExist("A pas with the name "+ workoutPasDTO.getName() +" already exist");
//        }else {
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

//        int position = -1;
//
//        WorkoutDTO oldRealmPlan = realm.where(WorkoutDTO.class).equalTo("id", planID).findFirst();
//
//        RealmList<WorkoutPasDTO> realmPas = oldRealmPlan.getWorkoutPasses();
//        for (int i = 0; i < realmPas.size(); i++) {
//            if(checkName(newPasName)){
//                throw new ExceptionNameAlreadyExist("A pas by the name "+newPasName+" already exist");
//            }
//            if (realmPas.get(i).getName().equals(oldPasName)) {
//                position = i;
//                break;
//            }
//        }
//
//        if (position == -1) {
//            throw new ExceptionPasDoesntExist("The pas doesnt exist");
//        } else {
//            WorkoutPasDTO updatedRealmPas = new WorkoutPasDTO();
//            RealmList<ExerciseGoals> exercises = oldRealmPlan.getWorkoutPasses().get(position).getExercises();
//
//            updatedRealmPas.setName(newPasName);
//            for(int i = 0; i < exercises.size(); i++){
//                updatedRealmPas.setExerciseGoal(oldRealmPlan.getWorkoutPasses().get(i).getExercises());
//            }
//            WorkoutPasDTO oldRealmPas = realm.where(WorkoutPasDTO.class).equalTo("name", oldPasName).findFirst();
//            RealmList<ExerciseGoals> goals = oldRealmPas.getExercises();
//        WorkoutPasDTO pas = new WorkoutPasDTO();
//        RealmList<WorkoutPasDTO> passes = oldRealmPlan.getWorkoutPasses();
        WorkoutPasDTO pas = realm.where(WorkoutPasDTO.class).equalTo("id", pasID).findFirst();
//        for (int i = 0; i < passes.size(); i++) {
//            if (passes.get(i).getID() == pasID) {
//                pas = passes.get(i);
//            }
//        }

        realm.beginTransaction();
        pas.setName(newPasName);
//            for(int i = 0; i < goals.size(); i++){
//                goals.get(i).deleteFromRealm();
//            }
//            oldRealmPas.deleteFromRealm();
        realm.commitTransaction();
//        }
    }

    /**
     * Deletes a pas from a plan
     *
     * @param pasId  the id of the pas you want to delete
     * @throws Exception if it doesn't exist in the plan
     */
    public void deletePas(int pasId) throws ExceptionPasDoesntExist {

//        int position = -1;
//
//        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("id", planId).findFirst();
//
//        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkoutPasses();
//        for (int i = 0; i < realmPas.size(); i++) {
//            if (realmPas.get(i).getID()==(pasId)) {
//                position = i;
//                break;
//            }
//        }
//
//        if (position == -1) {
//            throw new ExceptionPasDoesntExist("The pas "+getPasses()+" in " + planId + " doesnt exist");
//        } else {
//            RealmList<ExerciseGoals> goals = pas.getExercises();


        WorkoutPasDTO pas = realm.where(WorkoutPasDTO.class).equalTo("id", pasId).findFirst();
        RealmList<ExerciseGoals> goals = pas.getExercises();

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
//
//        int position = -1;
//
//        ExerciseDAO exerciseDAO = new ExerciseDAO();
//        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();

        WorkoutPasDTO realmPas = realm.where(WorkoutPasDTO.class).equalTo("id", pasID).findFirst();
        try {
            ExerciseGoals goal = realm.where(ExerciseGoals.class).findAll().last();
            id = goal.getID() + 1;
        }catch (IndexOutOfBoundsException e){
            id = 1;
        }
        for (int i = 0; i < realmPas.getExercises().size(); i++) {
            if (realmPas.getExercises().get(i).getName().equals(exerciseName)) {
                throw new ExceptionNameAlreadyExist("The exercise " + exerciseName + " already exist in a pas");
            }
        }
//              else {
//                if (realmPas.get(i).getName().equals(pasName)) {
//                    position = i;
//                    break;
//                }
//            }
//
//
//        if (position == -1) {
//            throw new ExceptionPasDoesntExist("The pas "+pasName+" in " + planName + " doesnt exist");
//        } else {
//
//            try {
//                ExerciseGoals goal = realm.where(ExerciseGoals.class).findAll().last();
//                id = goal.getID() + 1;
//            }catch (IndexOutOfBoundsException e){
//                id = 1;
//            }
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

//        int position = -1;
//
//        WorkoutDTO realmPlan = realm.where(WorkoutDTO.class).equalTo("name", planName).findFirst();
//
//        RealmList<WorkoutPasDTO> realmPas = realmPlan.getWorkoutPasses();
//        for (int i = 0; i < realmPas.size(); i++) {
//            if (realmPas.get(i).getName().equals(pasName)) {
//                position = i;
//                break;
//            }
//        }
//
//        if (position == -1) {
//            throw new ExceptionPasDoesntExist("The pas "+pasName+" in " + planName + " doesnt exist");
//        } else {
        realm.beginTransaction();
        ExerciseGoals goal = realm.where(ExerciseGoals.class).equalTo("id", exerciseGoalID).findFirst();
        goal.deleteFromRealm();
//            for (int i = 0; i < exercises.size(); i++) {
//                if (exercises.get(i).getName().equals(exerciseName)) {
//                    realmPlan.getWorkoutPasses().get(position).getExercises().remove(i);
//                    exercises.deleteFromRealm(i);
//                    break;
//                }
//            }
        realm.commitTransaction();

    }

    public RealmList<WorkoutPasDTO> getAllPasses() throws IndexOutOfBoundsException{
        RealmResults<WorkoutPasDTO> passes = realm.where(WorkoutPasDTO.class).findAll();
        RealmList<WorkoutPasDTO> listPasses = new RealmList<>();
        listPasses.addAll(passes.subList(0, passes.size()));
        return listPasses;
    }

}
