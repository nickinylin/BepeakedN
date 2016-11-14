package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;

/**
 * Created by sebho on 14-11-2016.
 */

public class WorkoutDTO {
    ArrayList<WorkoutPasDTO> workouts;

    public WorkoutDTO(ArrayList<WorkoutPasDTO> workouts) {
        this.workouts = workouts;
    }

    public ArrayList<WorkoutPasDTO> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<WorkoutPasDTO> workouts) {
        this.workouts = workouts;
    }


}
