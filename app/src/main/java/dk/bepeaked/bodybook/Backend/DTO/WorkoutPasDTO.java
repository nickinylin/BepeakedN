package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDTO extends RealmObject {

    String name;
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
