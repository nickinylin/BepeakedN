package dk.bepeaked.bodybook.Backend.DTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Nicki on 14/11/16.
 */

public class ExerciseDTO extends RealmObject{

    @PrimaryKey
    private String name, desc;

    private RealmList<Set> set = new RealmList<Set>();

    public ExerciseDTO(String name, String desc){
        this.name = name;
        this.desc = desc;
    }

    public void addSet(Set set){
        this.set.add(set);
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


}
