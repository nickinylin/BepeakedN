package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDTO {

    ArrayList<ExerciseDTO> exercises;

    public WorkoutPasDTO (ArrayList<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }

    public ArrayList<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }


}
