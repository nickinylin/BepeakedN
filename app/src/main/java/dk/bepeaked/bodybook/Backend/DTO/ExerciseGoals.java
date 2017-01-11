package dk.bepeaked.bodybook.Backend.DTO;

import io.realm.RealmObject;

/**
 * Created by sebho on 06-01-2017.
 */

public class ExerciseGoals extends RealmObject{
    String name;
    int set, reps, id;
    public ExerciseGoals(){}

    public ExerciseGoals(int id, String name, int set, int reps){
        this.name = name;
        this.set = set;
        this.reps = reps;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }
}
