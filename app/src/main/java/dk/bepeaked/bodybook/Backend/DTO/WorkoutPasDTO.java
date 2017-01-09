package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDTO extends RealmObject {

    @PrimaryKey
    String name;

    RealmList<StringObjectToRealm> exercises;
    Goals goals = new Goals();
    public WorkoutPasDTO() {}

    public WorkoutPasDTO (String name, RealmList<StringObjectToRealm> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public RealmList<StringObjectToRealm> getExercises() {
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
