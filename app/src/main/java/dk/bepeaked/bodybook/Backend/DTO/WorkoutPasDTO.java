package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDTO extends RealmObject {

    String name;
    RealmList<ExerciseDTO> exercises;

    public WorkoutPasDTO() {}

    public WorkoutPasDTO (String name, RealmList<ExerciseDTO> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public RealmList<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(RealmList<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }


}
