package dk.bepeaked.bodybook.Backend.DTO;

import java.security.PublicKey;
import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sebho on 14-11-2016.
 */

public class WorkoutDTO extends RealmObject {

    @PrimaryKey
    String name;
    RealmList<WorkoutPasDTO> workouts;

    public WorkoutDTO(){}

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
