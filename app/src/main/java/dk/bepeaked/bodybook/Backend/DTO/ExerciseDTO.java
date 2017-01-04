package dk.bepeaked.bodybook.Backend.DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmModel;

/**
 * Created by Nicki on 14/11/16.
 */

public class ExerciseDTO {

    private String name, desc;
    private RealmList<Set> set;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public ExerciseDTO(String name, String desc, RealmList<Set> set){
        this.name = name;
        this.desc = desc;
        this.set = set;
    }
    public void addSet(double weight, int reps, double rm, String date){
        Date realDate = null;
        try {
            realDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set newSet = new Set(weight, reps, realDate, rm);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RealmList<Set> getSet() {
        return set;
    }

    public void setSet(RealmList<Set> set) {
        this.set = set;
    }


    public class Set implements RealmModel {
        private int reps;
        private double weight, rm;
        private Date date;

        public Set (double weight, int reps, Date date, double rm) {
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

}
