package dk.bepeaked.bodybook.Backend.DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by sebho on 04-01-2017.
 */
public class SetDTO extends RealmObject {
    private int reps;
    private double weight, rm;
    private Date date;

    public SetDTO(){}

    public SetDTO(String exerciseName, double weight, int reps, Date date, double rm) {
        this.weight = weight;
        this.reps = reps;
        this.date = date;
        this.rm = rm;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
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

    public double getRm() {
        return rm;
    }

    public void setRm(double rm) {
        this.rm = rm;
    }
}
