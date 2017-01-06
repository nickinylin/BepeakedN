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
    ArrayList<String> exercises;
    Goals goals = new Goals();
    public WorkoutPasDTO() {}

    public WorkoutPasDTO (String name, ArrayList<String> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public ArrayList<String> getExercises() {
        return exercises;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Goals getGoals(){
        return goals;
    }

    public void addGoals(String name, int set, int reps){
        goals.setName(name);
        goals.setSet(set);
        goals.setReps(reps);
    }
}
