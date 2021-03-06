package dk.bepeaked.bodybook.Backend.DTO;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Nicki on 14/11/16.
 */

public class WorkoutPasDTO extends RealmObject {

    @PrimaryKey
    int id;
    String name;

    RealmList<ExerciseGoals> exercises = new RealmList  <ExerciseGoals>();
    public WorkoutPasDTO() {}

    public WorkoutPasDTO (int id, String name, RealmList<ExerciseGoals> exercises) {
        this.name = name;
        this.exercises = exercises;
        this.id = id;
    }

    public RealmList<ExerciseGoals> getExercises() {
        return exercises;
    }

    public void setExerciseGoal(RealmList<ExerciseGoals> exerciseGoals){
        this.exercises = (exerciseGoals);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getID(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }



}
