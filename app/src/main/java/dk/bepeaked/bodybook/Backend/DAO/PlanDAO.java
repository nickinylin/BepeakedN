package dk.bepeaked.bodybook.Backend.DAO;

import android.app.Activity;

import java.util.ArrayList;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.ExerciseGoals;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutDTO;
import dk.bepeaked.bodybook.Backend.DTO.WorkoutPasDTO;
import dk.bepeaked.bodybook.Backend.Exception.ExceptionNameAlreadyExist;
import io.realm.RealmList;

public class PlanDAO {
    ExcelDAO dao = new ExcelDAO();
    WorkoutController wc = new WorkoutController();
    private ArrayList<WorkoutDTO> savedDTO = new ArrayList<>();

    public void getWorkouts(Activity act, String file){
        ArrayList<WorkoutDTO> dtos = new ArrayList<>();
        ArrayList<String[]> data = dao.readCsv(act, file);
        ArrayList<String[]> cleanData = new ArrayList<>();
        ArrayList<ArrayList<String>> cleanData2 = new ArrayList<>();
        RealmList<ExerciseDTO> exes = wc.getAllExercises();
        for(int i = 0; i < data.size(); i++){
            cleanData.add(data.get(i));
        }
        for(int i = 0; i < cleanData.size(); i++){
            ArrayList<String> string = new ArrayList<>();
            for(int j = 0; j < cleanData.get(i).length; j++){
                if (cleanData.get(i)[j].equals("") || cleanData.get(i)[j].equals(" ") || cleanData.get(i)[j].equals(null)) {
                }else{
                    string.add(cleanData.get(i)[j]);
                }
            }
            if(string.size() > 0){
                cleanData2.add(string);
            }
        }
        for(int i = 0; i < cleanData2.size(); i++){
            WorkoutDTO dto = new WorkoutDTO();
            int indexStart;
            int indexEnd = 0;
            String[] nameSplit;
            RealmList<WorkoutPasDTO> passes = new RealmList<>();
            String name;
            if(cleanData2.get(i).get(0).contains("Plan")){
                indexStart = i;
                nameSplit = cleanData2.get(i).get(0).split(":");
                name = nameSplit[1];
                for(int j = i+1; j < cleanData2.size(); j++){
                    if(cleanData2.get(j).get(0).contains("Plan")){
                        indexEnd = j;
                        break;
                    }
                }
                for (int l = 0; l < 2; l++) {
                    WorkoutPasDTO pas = new WorkoutPasDTO();
                    RealmList<ExerciseDTO> exercises = new RealmList<>();
                    RealmList<ExerciseGoals> goals = new RealmList<>();
                    String pasName = cleanData2.get(indexStart + 1).get(l);
                    pas.setName(pasName);

                    for (int k = indexStart + 2; k < indexEnd - 1; k++) {
                        for (int m = 0; m < exes.size(); m++) {
                            if (exes.get(m).getName().equals(cleanData2.get(k).get(l).split("\\.")[0])) {
                                exercises.add(exes.get(m));
                            } else {
                                //TODO fejlmeddelelse hvis øvelsen ikke findes
                            }
                            String goalName = cleanData2.get(k).get(l).split("\\.")[0];
                            int set = Integer.parseInt(cleanData2.get(k).get(l).split("\\.")[1].split("x")[0]);
                            int reps = Integer.parseInt(cleanData2.get(k).get(l).split("\\.")[1].split("x")[1]);
                    //        goals.add(new ExerciseGoals(goalName, set, reps));
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
        for (int i = 0; i < dtos.size(); i++){
            try {
                wc.addPlan(dtos.get(i).getName());
            } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
                exceptionNameAlreadyExist.printStackTrace();
            }
            RealmList<WorkoutPasDTO> workoutPasDTOs = dtos.get(i).getWorkoutPasses();
            for (int j = 0; j < workoutPasDTOs.size(); j++) {
//                try {
//                    wc.addNewPasToPlan(dtos.get(i).getName(), workoutPasDTOs.get(j).getName());
//                } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
//                    exceptionNameAlreadyExist.printStackTrace();
                }
//                RealmList<ExerciseGoals> exerciseDTOs = workoutPasDTOs.get(j).getExercises();
//                for(int k = 0; k < exerciseDTOs.size(); k++) {
//                    try {
//                        wc.addExerciseToPas(dtos.get(i).getName(), workoutPasDTOs.get(j).getName(), exerciseDTOs.get(k).getName(), exerciseDTOs.get(k).getSet(), exerciseDTOs.get(k).getReps());
//                    } catch (ExceptionPasDoesntExist exceptionPasDoesntExist) {
//                        exceptionPasDoesntExist.printStackTrace();
//                    } catch (ExceptionNameAlreadyExist exceptionNameAlreadyExist) {
//                        exceptionNameAlreadyExist.printStackTrace();
//                    }
//                }
//            }
        }
    }
}
