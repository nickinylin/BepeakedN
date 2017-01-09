package dk.bepeaked.bodybook.Backend.DTO;

import io.realm.RealmObject;

/**
 * Created by sebho on 06-01-2017.
 */

public class ExerciseGoals extends RealmObject{
    String name;
    int set, reps;
    public ExerciseGoals(){}

    public ExerciseGoals(String name, int set, int reps){
        this.name = name;
        this.set = set;
        this.reps = reps;
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
}
