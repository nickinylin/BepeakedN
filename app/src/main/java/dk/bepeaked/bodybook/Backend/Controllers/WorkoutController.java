package dk.bepeaked.bodybook.Backend.Controllers;

import android.util.Log;

import dk.bepeaked.bodybook.Backend.DAO.ExerciseDAO;
import dk.bepeaked.bodybook.Backend.DAO.SetDAO;
import dk.bepeaked.bodybook.Backend.DAO.WorkoutDAO;
import dk.bepeaked.bodybook.Backend.DAO.WorkoutPasDAO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseGoals;
import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
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

    public RealmList<WorkoutDTO> getPlans() {
        realmListWorkoutDTO = new RealmList<WorkoutDTO>();
        realmListWorkoutDTO = workoutDAO.getPlans();
        return realmListWorkoutDTO;
    }

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

    public RealmList<WorkoutPasDTO> getPasses(String workoutPlan) {
        realmListWorkoutPasDTO = new RealmList<WorkoutPasDTO>();

        String workoutname;
        workoutname = getSpecificPlan(workoutPlan).getName();

        RealmList<WorkoutPasDTO> passes = workoutPasDAO.getPasses(workoutname);

        return passes;

    }

    public WorkoutPasDTO getSpecificPas(String planName, String pasName) {
        realmListWorkoutPasDTO = getPasses(planName);

        for (int i = 0; i < realmListWorkoutPasDTO.size(); i++) {
            if (realmListWorkoutPasDTO.get(i).getName().equals(pasName)) {
                workoutPasDTO = realmListWorkoutPasDTO.get(i);
            }
        }
        return workoutPasDTO;
    }

    public RealmList<ExerciseDTO> getExercisesFromPas(String workoutPlanName, String workoutPasName) {
        try {
            realmListExerciseDTO = exerciseDAO.getExercisesInPas(workoutPlanName, workoutPasName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realmListExerciseDTO;
    }

    //Exercises

    public RealmList<SetDTO> getSetFromSpecificExercise(String planName, String pasName, String exerciseName) {
        realmListExerciseDTO = new RealmList<ExerciseDTO>();
        realmListExerciseDTO = getExercisesFromPas(planName, pasName);
        realmListSetDTO = new RealmList<SetDTO>();
        for (int i = 0; i < realmListExerciseDTO.size(); i++) {
            if (realmListExerciseDTO.get(i).getName().equals(exerciseName)) {
                realmListSetDTO = realmListExerciseDTO.get(i).getSet();
                break;
            }
        }
        return realmListSetDTO;
    }

    public void addPlan(String planName) {
        workoutDAO.newPlan(new WorkoutDTO(planName, new RealmList<WorkoutPasDTO>()));
    }

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

    public void addExerciseToPas(String planName, String pasName, String exerciseName, int sets, int reps) {

        ExerciseGoals newExercise = new ExerciseGoals(exerciseName, sets, reps);
        try {
            workoutPasDAO.addExerciseToPas(planName, pasName, exerciseName, sets, reps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RealmList<ExerciseDTO> getAllExercises() {
        return exerciseDAO.getExercises();
    }

//    public RealmList<SetDTO> getSetsFromExercise(String exerciseName){
//
//        setDAO.
//    }



}
