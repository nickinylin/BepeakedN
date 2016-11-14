package dk.bepeaked.bodybook.Backend.DTO;

import java.util.Date;

/**
 * Created by Nicki on 14/11/16.
 */

public class ExerciseDTO {

    private int weight;
    private int reps;
    private int RM1;
    private Date date;

    public ExerciseDTO (int weight, int reps, int RM1, Date date) {
        this.weight = weight;
        this.reps = reps;
        this.RM1 = RM1;
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

    public int getRM1() {
        return RM1;
    }

    public void setRM1(int RM1) {
        this.RM1 = RM1;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
