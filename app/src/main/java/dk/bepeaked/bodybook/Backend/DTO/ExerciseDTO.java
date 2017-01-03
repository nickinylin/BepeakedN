package dk.bepeaked.bodybook.Backend.DTO;

import java.util.Date;

/**
 * Created by Nicki on 14/11/16.
 */

public class ExerciseDTO {

    private String name;
    //TODO LAV SOM I ExerciseDAO
    private int weight, reps;
    private Date date;

    public ExerciseDTO (int weight, int reps, Date date) {
        this.weight = weight;
        this.reps = reps;
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
