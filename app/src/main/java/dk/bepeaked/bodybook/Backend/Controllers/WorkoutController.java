package dk.bepeaked.bodybook.Backend.Controllers;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dk.bepeaked.bodybook.Backend.DAO.ExerciseDAO;
import dk.bepeaked.bodybook.Backend.DAO.SetDAO;
import dk.bepeaked.bodybook.Backend.DAO.WorkoutDAO;
import dk.bepeaked.bodybook.Backend.DAO.WorkoutPasDAO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseGoals;
import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionCantDelete;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionExerciseDoesntExist;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionPasDoesntExist;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionWrongInput;

import io.realm.RealmList;


import static dk.bepeaked.bodybook.R.string.set;

/**
 * Created by Nicki on 05/01/17.
 */

public class WorkoutController {

    WorkoutDAO workoutDAO = new WorkoutDAO();
    WorkoutDTO workoutDTO = new WorkoutDTO();
    WorkoutPasDTO workoutPasDTO = new WorkoutPasDTO();
    WorkoutPasDAO workoutPasDAO = new WorkoutPasDAO();
    ExerciseDTO exerciseDTO = new ExerciseDTO();
    ExerciseDAO exerciseDAO = new ExerciseDAO();
    SetDTO setDTO = new SetDTO();
    SetDAO setDAO = new SetDAO();
    RealmList<WorkoutDTO> realmListWorkoutDTO;
    RealmList<WorkoutPasDTO> realmListWorkoutPasDTO;
    RealmList<ExerciseDTO> realmListExerciseDTO;
    RealmList<SetDTO> realmListSetDTO;

    //*******************Planer**************************
    //Create
    /**
     * Adds a new plan with an empty pas list
     * @param planName the name of the new plan
     */
    public void addPlan(String planName) throws ExceptionNameAlreadyExist {
        workoutDAO.newPlan(new WorkoutDTO(planName, new RealmList<WorkoutPasDTO>()));
    }

    //READ
    /**
     * Gets a list of all the plans
     * @return RealmList<WorkoutDTO>
     */
    public RealmList<WorkoutDTO> getPlans() {
        realmListWorkoutDTO = new RealmList<WorkoutDTO>();
        realmListWorkoutDTO = workoutDAO.getPlans();
        return realmListWorkoutDTO;
    }
    //UPDATE
    /**
     * Updates a plans name
     * @param oldPlan old name
     * @param newPlan new name
     */
    public void updatePlanName(String oldPlan, String newPlan) throws ExceptionPasDoesntExist, ExceptionNameAlreadyExist {
        workoutDAO.updatePlanName(oldPlan, newPlan);
    }

    //DELETE
    /**
     * Deletes a plan from the database (THIS IS PERMANENT)
     * @param planName the plan to be deleted
     */
    public void deletePlan(String planName) throws ExceptionPasDoesntExist {
        workoutDAO.deletePlan(planName);
    }

    /**
     * Gets a specific plan from the database
     * @param planName the name of the plan
     * @return WorkoutDTO (plan object)
     */
    public WorkoutDTO getSpecificPlan(String planName) {
        realmListWorkoutDTO = getPlans();

        for (int i = 0; i < realmListWorkoutDTO.size(); i++) {
            if (realmListWorkoutDTO.get(i).getName().equals(planName)) {
                workoutDTO = realmListWorkoutDTO.get(i);
                break;
            }
        }
        return workoutDTO;
    }

    //********************Pas***************************

    //CREATE
    /**
     * Adds a new pas to a plan
     * @param planName
     * @param pasName
     * @throws ExceptionNameAlreadyExist if a pas of that name already exist (anywhere)
     */
    public void addNewPasToPlan(String planName, String pasName) throws ExceptionNameAlreadyExist {

            realmListWorkoutDTO = workoutDAO.getPlans();
            for (int i = 0; i < realmListWorkoutDTO.size(); i++) {
                realmListWorkoutPasDTO = realmListWorkoutDTO.get(i).getWorkoutPasses();
                for (int l = 0; l < realmListWorkoutPasDTO.size(); l++) {
                    if (realmListWorkoutPasDTO.get(l).getName().equals(pasName)) {
                        throw new ExceptionNameAlreadyExist("The name already exists in another pas");
                    }
                }
            }

        workoutPasDTO = new WorkoutPasDTO(pasName, new RealmList<ExerciseGoals>());
        workoutPasDAO.newPas(planName, workoutPasDTO);
    }

    //READ
    /**
     * Gets a list of all the passes in a workoutplan
     * @param workoutPlan the plan name you want the list of passes from
     * @return RealmList<WorkoutDTO>
     */
    public RealmList<WorkoutPasDTO> getPasses(String workoutPlan) {
        realmListWorkoutPasDTO = new RealmList<WorkoutPasDTO>();
        RealmList<WorkoutPasDTO> passes = null;

        String workoutname;
        workoutname = getSpecificPlan(workoutPlan).getName();

        try {
            passes = workoutPasDAO.getPasses(workoutname);
        } catch (NullPointerException e) {
            passes = null;
        }

        return passes;
    }

    //UPDATE
    /**
     * Updates a pas name
     * @param planName
     * @param oldPasName
     * @param newPasName
     * @throws ExceptionPasDoesntExist if the pas doesnt exist
     */
    public void updatePasName(String planName, String oldPasName, String newPasName) throws ExceptionPasDoesntExist, ExceptionNameAlreadyExist {

        workoutPasDAO.updatePasName(planName, oldPasName, newPasName);
    }

    //DELETE
    /**
     * Deletes a pas from a plan (PERMANENTLY)
     * @param planName
     * @param pasName
     * @throws ExceptionPasDoesntExist if the pas doenst exist
     */
    public void deletePas(String planName, String pasName) throws ExceptionPasDoesntExist {
        workoutPasDAO.deletePas(planName, pasName);
    }

    /**
     * Gets a list of the names of passes from a plan
     * @param planName the plans name
     * @return ArrayList<String>
     */
    public ArrayList<String> getPasNamesFromPlan (String planName) {
        RealmList<WorkoutPasDTO> newList;
        newList = getPasses(planName);
        ArrayList<String> pasNames = new ArrayList<String>();
        try {
            for (int i = 0; i < newList.size(); i++) {
                pasNames.add(newList.get(i).getName());
            }
        }catch(Exception e){

        }
        return pasNames;
    }

    /**
     * Gets a specifik pas object from a plan
     * @param planName
     * @param pasName
     * @return WorkoutPasDTO
     */
    public WorkoutPasDTO getSpecificPas(String planName, String pasName) {
        realmListWorkoutPasDTO = getPasses(planName);

        for (int i = 0; i < realmListWorkoutPasDTO.size(); i++) {
            if (realmListWorkoutPasDTO.get(i).getName().equals(pasName)) {
                workoutPasDTO = realmListWorkoutPasDTO.get(i);
            }
        }
        return workoutPasDTO;
    }

    /**
     * gets all the exercises added to a pass
     * @param workoutPlanName the plan its in
     * @param workoutPasName the pas they're in
     * @return RealmList<ExerciseDTO>
     */
    public RealmList<ExerciseDTO> getExercisesFromPas(String workoutPlanName, String workoutPasName) {
        try {
            realmListExerciseDTO = exerciseDAO.getExercisesInPas(workoutPlanName, workoutPasName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realmListExerciseDTO;
    }

    public ArrayList<String> getExercisesFromPasToArray(String planName, String pasName) {
        ArrayList<String> exerciseNames = new ArrayList<String>();
        RealmList<ExerciseDTO> newList;
        newList = getExercisesFromPas(planName, pasName);

        try {
            for (int i = 0; i < newList.size(); i++) {
                exerciseNames.add(newList.get(i).getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exerciseNames;


    }

    //************Exercises*************

    //CREATE
    public void createNewExercise(String exerciseName) throws ExceptionNameAlreadyExist {

        ExerciseDTO newExercise = new ExerciseDTO(exerciseName, null, null, null, null, new RealmList<SetDTO>());
        exerciseDAO.newExercise(newExercise);
    }

    //READ
    /**
     * Gets a list of all the exercises in the exercise library
     * @return RealmList<ExerciseDTO>
     */
    public RealmList<ExerciseDTO> getAllExercises() {
        return exerciseDAO.getExercises();
    }

    public ArrayList<String> getAllExerciseNamesToArray() {
        RealmList<ExerciseDTO> newList;
        newList = getAllExercises();
        ArrayList<String> arrayListAllExerciseNames = new ArrayList<String>();

        for (int i = 0; i < newList.size(); i++) {
            arrayListAllExerciseNames.add(newList.get(i).getName());
        }
        return arrayListAllExerciseNames;
    }

    //UPDATE

    /**
     * Updates an exercise' name
     * @param oldName
     * @param newName
     */
//    public void updateExercise(String oldName, String newName){
//        exerciseDAO.updateExerciseName(oldName, newName);
//    }

    //DELETE
    /**
     * Deletes an exercise (PERMANENTLY)
     * @param name
     */
    public void deleteExercise(String name) throws ExceptionPasDoesntExist, ExceptionCantDelete {
        exerciseDAO.deleteExercise(name);
    }

    /**
     * Adds an existing exercise to a pas
     * @param planName The plan it's in
     * @param pasName the pas it's in
     * @param exerciseName the name of the exercise in the exercise library
     * @param sets the amount of sets (unique for the pas)
     * @param reps the amount of reps (unique for the pas)
     */
    public void addExerciseToPas(String planName, String pasName, String exerciseName, int sets, int reps) throws ExceptionPasDoesntExist, ExceptionNameAlreadyExist {

        ExerciseGoals newExercise = new ExerciseGoals(exerciseName, sets, reps);

        workoutPasDAO.addExerciseToPas(planName, pasName, exerciseName, sets, reps);

    }

    /**
     * Gets a specific exercise
     * @param name the name of the exercise
     * @return ExerciseDTO object
     * @throws ExceptionExerciseDoesntExist If the exercise doesn't exist
     */
    public ExerciseDTO getExercise(String name) throws ExceptionExerciseDoesntExist {
        ExerciseDTO exercise = null;
        realmListExerciseDTO = getAllExercises();
        for (int i = 0; i < realmListExerciseDTO.size(); i++) {
            if (realmListExerciseDTO.get(i).getName().equals(name)) {
                exercise = realmListExerciseDTO.get(i);
            }
        }
        if (exercise == null) {
            throw new ExceptionExerciseDoesntExist("Exercise " + name + " Doesn't exist");
        } else {
            return exercise;
        }
    }

    /**
     * Get's a list of all the sets added to an exercise
     * @param exerciseName
     * @return RealmList<SetDTO>
     * @throws ExceptionExerciseDoesntExist if named exercise doesn't exist
     */
    public RealmList<SetDTO> getSetsFromExercise(String exerciseName) throws ExceptionExerciseDoesntExist {

        exerciseDTO = getExercise(exerciseName);
        realmListSetDTO = exerciseDTO.getSets();
        return realmListSetDTO;
    }

    public void deleteExerciseFromPas(String planName, String pasName, String exerciseName) throws ExceptionPasDoesntExist {

        workoutPasDAO.removeExerciseFromPas(planName, pasName, exerciseName);
    }

    //****************************SETS*********************************
    /**
     * Adds a new set
     * @param exerciseName The exercise name
     * @param kg NOTE: if exercise requires
     * @param reps
     * @throws ExceptionWrongInput
     */
    public void addSet(String exerciseName, double kg, int reps) throws ExceptionWrongInput {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SetDTO newSet;
        if (kg == 0) {
            newSet = new SetDTO(0, reps, date, 0);
            setDAO.addSet(exerciseName, newSet);
        } else if (kg > 0) {

            if (reps == 37) {
                reps++;
            }
            //Brzycki 1RM formula
            Double RM = kg / ((37 / 36) - ((1 / 36) * reps));

            newSet = new SetDTO(kg, reps, date, RM);
            setDAO.addSet(exerciseName, newSet);
        } else {
            throw new ExceptionWrongInput("Weight input can't be less than 0");
        }
    }

    /**
     * Deletes a set from an exercise
     * @param exerciseName
     * @param position the position in the array (get this from the list in the gui)
     * @throws ExceptionExerciseDoesntExist if the exception doesn't exist
     *
     * +
     *
     */
    public void deleteSet(String exerciseName, int position) throws ExceptionExerciseDoesntExist {

        RealmList<SetDTO> sets = getSetsFromExercise(exerciseName);
        setDTO = sets.get(position);
        setDAO.deleteSet(exerciseName, setDTO);
    }

}
