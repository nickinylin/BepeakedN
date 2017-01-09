package dk.bepeaked.bodybook.Backend.Controllers;

import dk.bepeaked.bodybook.Backend.DAO.ExerciseDAO;
import dk.bepeaked.bodybook.Backend.DAO.WorkoutDAO;
import dk.bepeaked.bodybook.Backend.DAO.WorkoutPasDAO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
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

//    public RealmList<WorkoutPasDTO> getPasses(String workoutPlan) {
//        realmListWorkoutPasDTO = new RealmList<WorkoutPasDTO>();
//
//        workoutDTO = getSpecificPlan(workoutPlan);
//
//        return workoutDTO.getWorkouts();
//
//    }

//    public WorkoutPasDTO getSpecificPas(String planName, String pasName) {
//        realmListWorkoutPasDTO = getPasses(planName);
//
//        for (int i = 0; i < realmListWorkoutPasDTO.size(); i++) {
//            if (realmListWorkoutPasDTO.get(i).getName().equals(pasName)) {
//                return workoutPasDTO = realmListWorkoutPasDTO.get(i);
//            }
//        }
//        return null;
//    }

//    public RealmList<ExerciseDTO> getExercisesFromPas(String workoutPlanName, String workoutPasName) {
//        realmListWorkoutPasDTO = new RealmList<WorkoutPasDTO>();
//        realmListExerciseDTO = new RealmList<ExerciseDTO>();
//
//        realmListWorkoutPasDTO = getPasses(workoutPlanName);
//        for (int j = 0; j < realmListWorkoutPasDTO.size(); j++) {
//            if (realmListWorkoutPasDTO.get(j).getName().equals(workoutPasName)) {
//                realmListExerciseDTO = realmListWorkoutPasDTO.get(j).getExercises();
//                break;
//            }
//        }
//        return realmListExerciseDTO;
//
//    }

    //Exercises

//    public RealmList<SetDTO> getSetFromSpecificExercise(String planName, String pasName, String exerciseName) {
//        realmListExerciseDTO = new RealmList<ExerciseDTO>();
//        realmListExerciseDTO = getExercisesFromPas(planName, pasName);
//        realmListSetDTO = new RealmList<SetDTO>();
//        for (int i = 0; i < realmListExerciseDTO.size(); i++) {
//            if (realmListExerciseDTO.get(i).getName().equals(exerciseName)) {
//                realmListSetDTO = realmListExerciseDTO.get(i).getSet();
//                break;
//            }
//        }
//        return realmListSetDTO;
//    }

    public void addPlan(String planName) {
        workoutDAO.newPlan(new WorkoutDTO(planName, new RealmList<WorkoutPasDTO>()));
    }

//    public void addNewPasToPlan(String planName, String pasName) {
//        workoutPasDTO = new WorkoutPasDTO(pasName, new RealmList<ExerciseDTO>());
//        workoutPasDAO.newPas(workoutPasDTO);
//        workoutDTO = getSpecificPlan(planName);
//        workoutDAO.addWorkoutPas(planName, workoutPasDTO);
//        workoutDAO.updatePlan(planName, workoutDTO);
//    }

//    public void addPasToPlan(String planName, String pasName) {
//        workoutPasDTO = getSpecificPas(planName, pasName);
//        workoutDTO = getSpecificPlan(planName);
//        workoutDTO.addWorkoutPas(workoutPasDTO);
//        workoutDAO.updatePlan(planName, workoutDTO);
//    }

    public void addExerciseToPas(String exerciseName) {

    }

//    public RealmList<ExerciseDTO> getAllExercises() {
//
//    }


}
