package dk.bepeaked.bodybook.Backend.DTO;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Nicki on 14/11/16.
 */

public class ExerciseDTO extends RealmObject{

    @PrimaryKey
    int id;
    private String name;
    private String desc1;
    private String desc2;

    private String imagePath1, imagePath2;

    private RealmList<SetDTO> set = new RealmList<SetDTO>();

    public ExerciseDTO(){}

    public ExerciseDTO(int id, String name, String desc1, String desc2, String imagePath1, String imagePath2, RealmList<SetDTO> set){
        this.name = name;
        this.desc1 = desc1;
        this.desc2 = desc2;
        this.imagePath1 = imagePath1;
        this.imagePath2 = imagePath2;
        this.set = set;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public RealmList<SetDTO> getSets() {
        return set;
    }

    public void setSet(RealmList<SetDTO> set) {
        this.set = set;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getImagePath1() {
        return imagePath1;
    }

    public void setImagePath1(String imagePath) {
        this.imagePath1 = imagePath;
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath) {
        this.imagePath2 = imagePath;
    }

    public int getID(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}
