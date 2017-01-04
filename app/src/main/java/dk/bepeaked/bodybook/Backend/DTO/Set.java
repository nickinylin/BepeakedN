package dk.bepeaked.bodybook.Backend.DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by sebho on 04-01-2017.
 */
public class Set extends RealmObject {
    private int reps;
    private double weight, rm;
    private Date date;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");


    public Set(double weight, int reps, String dateString, double rm) {
        this.weight = weight;
        this.reps = reps;
        try {
            this.date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
