package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sebho on 14-11-2016.
 */

public class WorkoutDTO extends RealmObject {

    String name;
    RealmList<WorkoutPasDTO> workouts;

    public WorkoutDTO(String name, RealmList<WorkoutPasDTO> workouts) {
        this.name = name;
        this.workouts = workouts;
    }

    public RealmList<WorkoutPasDTO> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(RealmList<WorkoutPasDTO> workouts) {
        this.workouts = workouts;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
