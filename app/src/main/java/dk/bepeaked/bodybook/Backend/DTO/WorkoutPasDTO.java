package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDTO extends RealmObject {

    @PrimaryKey
    String name;
    RealmList<ExerciseDTO> exercises;
    HashMap<String, int[]> hm = new HashMap<>();

    public WorkoutPasDTO() {}

    public WorkoutPasDTO (String name, RealmList<ExerciseDTO> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public RealmList<ExerciseDTO> getExercises() {
        return exercises;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public HashMap<String, int[]> getSetReps(){
        return hm;
    }

    public void addEntry(String name, int set, int reps){
        hm.put(name, new int[]{set, reps});
    }
}
