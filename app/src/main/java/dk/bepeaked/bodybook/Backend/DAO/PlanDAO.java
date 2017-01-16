package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseGoals;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import io.realm.RealmList;

public class PlanDAO {
    CsvDAO dao = new CsvDAO();
    WorkoutController wc = new WorkoutController();

    public void getWorkouts(Activity act, String file) {
        ArrayList<WorkoutDTO> dtos = new ArrayList<>();
        ArrayList<String[]> data = dao.readCsv(act, file);
        ArrayList<String[]> cleanData = new ArrayList<>();
        ArrayList<ArrayList<String>> cleanData2 = new ArrayList<>();
        RealmList<ExerciseDTO> exes = wc.getAllExercises();
        for (int i = 0; i < data.size(); i++) {
            cleanData.add(data.get(i));
        }
        for (int i = 0; i < cleanData.size(); i++) {
            ArrayList<String> string = new ArrayList<>();
            for (int j = 0; j < cleanData.get(i).length; j++) {
                if (cleanData.get(i)[j].equals("") || cleanData.get(i)[j].equals(" ") || cleanData.get(i)[j].equals(null)) {
                } else {
                    string.add(cleanData.get(i)[j]);
                }
            }
            if (string.size() > 0) {
                cleanData2.add(string);
            }
        }
        for (int i = 0; i < cleanData2.size(); i++) {
            WorkoutDTO dto = new WorkoutDTO();
            int indexStart;
            int indexEnd = 0;
            String[] nameSplit;
            RealmList<WorkoutPasDTO> passes = new RealmList<>();
            String name;
            boolean exist = false;
            if (cleanData2.get(i).get(0).contains("Plan")) {
                indexStart = i;
                nameSplit = cleanData2.get(i).get(0).split(":");
                name = nameSplit[1];
                RealmList<WorkoutDTO> plans = wc.getPlans();
                for (int j = 0; j < plans.size(); j++) {
                    if (name.equals(plans.get(j).getName())) {
                        exist = true;
                    }
                }
                if (!(exist)) {
                    for (int j = i + 1; j < cleanData2.size(); j++) {
                        if (cleanData2.get(j).get(0).contains("Plan")) {
                            indexEnd = j;
                            break;
                        }
                    }
                    if (indexEnd < indexStart) {
                        indexEnd = cleanData2.size();
                    }
                    for (int l = 0; l < cleanData2.get(indexStart + 1).size(); l++) {
                        WorkoutPasDTO pas = new WorkoutPasDTO();
                        RealmList<ExerciseGoals> goals = new RealmList<>();
                        String pasName = cleanData2.get(indexStart + 1).get(l);
                        pas.setName(pasName);

                        for (int k = indexStart + 2; k < indexEnd; k++) {
                            for (int m = 0; m < exes.size(); m++) {
                                if (exes.get(m).getName().equals(cleanData2.get(k).get(l).split(":")[0])) {
                                    String goalName = cleanData2.get(k).get(l).split(":")[0];
                                    int set = Integer.parseInt(cleanData2.get(k).get(l).split(":")[1].split("x")[0].replaceAll("\\D+", ""));
                                    int reps = Integer.parseInt(cleanData2.get(k).get(l).split(":")[1].split("x")[1].replaceAll("\\D+", ""));
                                    ExerciseGoals goal = new ExerciseGoals();
                                    goal.setName(goalName);
                                    goal.setSet(set);
                                    goal.setReps(reps);
                                    goals.add(goal);
                                    break;
                                } else {
                                    //TODO fejlmeddelelse hvis øvelsen ikke findes
                                }
                            }
                        }
                        pas.setExerciseGoal(goals);
                        passes.add(pas);
                    }
                    dto.setWorkoutPas(passes);
                    dto.setName(name);
                    dtos.add(dto);
                }
            }
            if(!(exist)){
                try {
                    wc.addPlan(dtos.get(i).getName());
                } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
                    exceptionNameAlreadyExist.printStackTrace();
                }
                int planID = 0;
                for (int l = 0; l < wc.getPlans().size(); l++) {
                    if (wc.getPlans().get(l).getName().equals(dtos.get(i).getName())) {
                        planID = wc.getPlans().get(l).getID();
                    }
                }

                RealmList<WorkoutPasDTO> workoutPasDTOs = dtos.get(i).getWorkoutPasses();
                for (int j = 0; j < workoutPasDTOs.size(); j++) {
                    try {
                        wc.addNewPasToPlan(planID, workoutPasDTOs.get(j).getName());
                    } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
                        exceptionNameAlreadyExist.printStackTrace();
                    }
                    Log.d("sebby", "getWorkouts: " + workoutPasDTOs.get(j).getExercises().size());
                    for (int l = 0; l < workoutPasDTOs.get(j).getExercises().size(); l++) {

                        RealmList<ExerciseGoals> goals = workoutPasDTOs.get(j).getExercises();
                        RealmList<ExerciseDTO> exercises = wc.getAllExercises();
                        boolean exists = false;
                        for (int k = 0; k < exercises.size(); k++) {
                            if (goals.get(l).getName().equals(exercises.get(k).getName())) {
                                exists = true;
                            }
                            if (exists) {
                                try {
                                    wc.createNewExercise(goals.get(l).getName());
                                } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
                                    exceptionNameAlreadyExist.printStackTrace();
                                }
                                break;
                            }
                        }

                        try {
                            int id = wc.getPasses(planID).get(j).getID();
                            wc.addExerciseToPas(id, goals.get(l).getName(), goals.get(l).getSet(), goals.get(l).getReps());
                        } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
                            exceptionNameAlreadyExist.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
