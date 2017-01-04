package dk.bepeaked.bodybook.Backend.DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nicki on 14/11/16.
 */

public class ExerciseDTO {

    private String name, desc;
    private ArrayList<Set> set;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public ExerciseDTO(String name, String desc, ArrayList<String[]> data){
        this.name = name;
        this.desc = desc;

        for(int i = 0; i < data.size(); i++){
            String[] singleSet = data.get(i);
            Date date = null;
            try {
                date = df.parse(singleSet[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            set.add(new Set(Integer.parseInt(singleSet[0]), Integer.parseInt(singleSet[1]), date));
        }
    }

    //TODO LAV SOM I ExerciseDAO


    public class Set{
        private int weight, reps;
        private Date date;

        public Set (int weight, int reps, Date date) {
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

}
