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
    int id;

    //*******************Planer**************************
    //Create
    /**
     * Adds a new plan with an empty pas list
     * @param planName the name of the new plan
     */
    public void addPlan(String planName) throws ExceptionNameAlreadyExist {
        try {
            workoutDTO = getPlans().last();
            id = workoutDTO.getID()+1;
        }catch (IndexOutOfBoundsException e){
            id = 1;
        }
        workoutDAO.newPlan(new WorkoutDTO(id, planName, new RealmList<WorkoutPasDTO>()));
    }

    //READ
    /**
     * Gets a list of all the plans
     * @return RealmList<WorkoutDTO>
     */
    public RealmList<WorkoutDTO> getPlans() throws IndexOutOfBoundsException{
        realmListWorkoutDTO = new RealmList<WorkoutDTO>();
        realmListWorkoutDTO = workoutDAO.getPlans();
        return realmListWorkoutDTO;
    }
    //UPDATE
    /**
     * Updates a plans name
     * @param planID old name
     * @param newName new name
     */
    public void updatePlanName(int planID, String newName) throws ExceptionPasDoesntExist, ExceptionNameAlreadyExist {
        workoutDAO.updatePlanName(planID, newName);
    }

    //DELETE
    /**
     * Deletes a plan from the database (THIS IS PERMANENT)
     * @param planID the plan to be deleted
     */
    public void deletePlan(int planID) throws ExceptionPasDoesntExist {
        workoutDAO.deletePlan(planID);
    }

    /**
     * Gets a specific plan from the database
     * @param planID the name of the plan
     * @return WorkoutDTO (plan object)
     */
    public WorkoutDTO getSpecificPlan(int planID) {
        realmListWorkoutDTO = getPlans();

        for (int i = 0; i < realmListWorkoutDTO.size(); i++) {
            if (realmListWorkoutDTO.get(i).getID()==planID) {
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
     * @param planId
     * @param pasName
     * @throws ExceptionNameAlreadyExist if a pas of that name already exist (anywhere)
     */
    public void addNewPasToPlan(int planId, String pasName) throws ExceptionNameAlreadyExist {

        int id;
        try{
            id = workoutPasDAO.getAllPasses().last().getID()+1;
        }catch (IndexOutOfBoundsException e){
            id = 1;
        }
        Log.d("LUKAS", "addNewPasToPlan: id "+ id);
        workoutPasDTO = new WorkoutPasDTO(id, pasName, new RealmList<ExerciseGoals>());
        workoutPasDAO.newPas(planId, workoutPasDTO);
    }

    //READ
    /**
     * Gets a list of all the passes in a workoutplan
     * @param planID the plan name you want the list of passes from
     * @return RealmList<WorkoutDTO>
     */
    public RealmList<WorkoutPasDTO> getPasses(int planID) throws IndexOutOfBoundsException {

        RealmList<WorkoutPasDTO> passes = new RealmList<>();
        passes = workoutPasDAO.getPasses(planID);

        return passes;
    }

    //UPDATE
    /**
     * Updates a pas name
     * @param pasID
     * @param newPasName
     * @throws ExceptionNameAlreadyExist if the pas doesnt exist
     */
    public void updatePasName(int pasID, String newPasName) throws ExceptionNameAlreadyExist {

        workoutPasDAO.updatePasName(pasID, newPasName);
    }

    //DELETE
    /**
     * Deletes a pas from a plan (PERMANENTLY)
     * @param pasID
     * @throws ExceptionPasDoesntExist if the pas doenst exist
     */
    public void deletePas(int pasID) throws ExceptionPasDoesntExist {
        workoutPasDAO.deletePas(pasID);
    }


    /**
     * Gets a list of the names of passes from a plan
     * @param planID the plans id
     * @return ArrayList<String>
     */
    public ArrayList<String> getPasNamesFromPlan (int planID) throws IndexOutOfBoundsException {
        RealmList<WorkoutPasDTO> newList;
        newList = getPasses(planID);
        ArrayList<String> pasNames = new ArrayList<>();
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
     * @param pasID
     * @return WorkoutPasDTO
     */
    public WorkoutPasDTO getSpecificPas(int pasID) {

        WorkoutPasDTO workoutPasDTO = workoutPasDAO.getPas(pasID);
        return workoutPasDTO;
    }

    /**
     * gets all the exercises added to a pass
     * @param pasID
     * @return RealmList<ExerciseDTO>
     */
    public RealmList<ExerciseDTO> getExercisesFromPas(int pasID) throws IndexOutOfBoundsException {
        realmListExerciseDTO = exerciseDAO.getExercisesInPas(pasID);
        return realmListExerciseDTO;
    }

    //************Exercises*************

    //CREATE
    public void createNewExercise(String exerciseName) throws ExceptionNameAlreadyExist {

        int id;
        RealmList<ExerciseDTO> exercise = getAllExercises();
        try {
            id = exercise.last().getID() + 1;
        }catch(IndexOutOfBoundsException e){
            id = 1;
        }

        for(int i = 0; i<exercise.size(); i++){
            if(exerciseName.equals(exercise.get(i).getName())){
                throw new ExceptionNameAlreadyExist("An exercise with the name "+ exerciseName +" already exist");
            }
        }
        ExerciseDTO newExercise = new ExerciseDTO(id, exerciseName, null, null, null, null, new RealmList<SetDTO>());
        exerciseDAO.newExercise(newExercise);
    }

    //READ
    /**
     * Gets a list of all the exercises in the exercise library
     * @return RealmList<ExerciseDTO>
     */
    public RealmList<ExerciseDTO> getAllExercises() throws IndexOutOfBoundsException{
        return exerciseDAO.getExercises();
    }

    public ArrayList<String> getExercisesFromPasToArray(int pasID) throws IndexOutOfBoundsException {
        ArrayList<String> exerciseNames = new ArrayList<>();
        RealmList<ExerciseDTO> newList;
        newList = getExercisesFromPas(pasID);


        for (int i = 0; i < newList.size(); i++) {
            exerciseNames.add(newList.get(i).getName());


        }
        return exerciseNames;
    }

    public ArrayList<String> getAllExerciseNamesToArray() throws IndexOutOfBoundsException{
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
     * @param exerciseID
     * @param newName
     */
    public void updateExercise(int exerciseID, String newName) throws ExceptionCantDelete {
        exerciseDAO.updateExerciseName(exerciseID, newName);
    }

    //DELETE
    /**
     * Deletes an exercise (PERMANENTLY)
     * @param exerciseID
     */
    public void deleteExercise(int exerciseID) throws ExceptionCantDelete {
        exerciseDAO.deleteExercise(exerciseID);
    }

    /**
     * Adds an existing exercise to a pas
     * @param pasID The plan it's in
     * @param exerciseName the name of the exercise in the exercise library
     * @param sets the amount of sets (unique for the pas)
     * @param reps the amount of reps (unique for the pas)
     */
    public void addExerciseToPas(int pasID, String exerciseName, int sets, int reps) throws ExceptionNameAlreadyExist {

        workoutPasDAO.addExerciseToPas(pasID, exerciseName, sets, reps);

    }

    /**
     * Gets a specific exercise
     * @param exerciseID the name of the exercise
     * @return ExerciseDTO object
     * @throws ExceptionExerciseDoesntExist If the exercise doesn't exist
     */
    public ExerciseDTO getExercise(int exerciseID){
        ExerciseDTO exercise = null;
        realmListExerciseDTO = getAllExercises();
        for (int i = 0; i < realmListExerciseDTO.size(); i++) {
            if (realmListExerciseDTO.get(i).getID()==exerciseID) {
                exercise = realmListExerciseDTO.get(i);
            }
        }
        return exercise;
    }

    public ExerciseDTO getExercise(String exerciseName){
        ExerciseDTO exercise = new ExerciseDTO();
        realmListExerciseDTO = getAllExercises();
        for (int i = 0; i < realmListExerciseDTO.size(); i++) {
            if (realmListExerciseDTO.get(i).getName().equals(exerciseName)) {
                exercise = realmListExerciseDTO.get(i);
            }
        }
        return exercise;
    }


    /**
     * Get's a list of all the sets added to an exercise
     * @param exerciseID
     * @return RealmList<SetDTO>
     * @throws ExceptionExerciseDoesntExist if named exercise doesn't exist
     */
    public RealmList<SetDTO> getSetsFromExercise(int exerciseID) throws ExceptionExerciseDoesntExist {

        exerciseDTO = getExercise(exerciseID);
        realmListSetDTO = exerciseDTO.getSets();
        return realmListSetDTO;
    }

    public void deleteExerciseFromPas(int exerciseGoalID) throws ExceptionPasDoesntExist {

        workoutPasDAO.removeExerciseFromPas(exerciseGoalID);
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
        int id;
        int repsudregning;
        try{
            id = setDAO.getAllSets().last().getId()+1;
        }catch (IndexOutOfBoundsException e){
            id = 1;
        }
        SetDTO newSet;

        if (kg == 0) {
            newSet = new SetDTO(id, exerciseName, 0, reps, date, 0);
            setDAO.addSet(exerciseName, newSet);
        } else if (kg > 0) {
            repsudregning = reps;

            if (reps == 37) {
                repsudregning++;
            }
            //Brzycki 1RM formula
            Double RM = kg / ((37 / 36) - ((1 / 36) * repsudregning));

            newSet = new SetDTO(id, exerciseName, kg, reps, date, RM);
            setDAO.addSet(exerciseName, newSet);
        } else {
            throw new ExceptionWrongInput("Weight input can't be less than 0");
        }
    }

    /**
     * Deletes a set from an exercise
     * @param setID
     * @throws ExceptionExerciseDoesntExist if the exception doesn't exist
     *
     * +
     *
     */
    public void deleteSet(int setID) {
        setDAO.deleteSet(setID);
    }

}
