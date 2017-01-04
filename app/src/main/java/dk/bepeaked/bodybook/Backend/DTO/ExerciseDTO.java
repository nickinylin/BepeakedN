package dk.bepeaked.bodybook.Backend.DTO;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Nicki on 14/11/16.
 */

public class ExerciseDTO extends RealmObject{

    @PrimaryKey
    private String name;
    private String desc1, desc2, imagePath;

    private RealmList<SetDTO> set = new RealmList<SetDTO>();

    public ExerciseDTO(){}

    public ExerciseDTO(String name, String desc){
        this.name = name;
        this.desc1 = desc;
    }

    public void addSet(SetDTO set){
        this.set.add(set);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc1;
    }

    public void setDesc(String desc) {
        this.desc1 = desc;
    }

    public RealmList<SetDTO> getSet() {
        return set;
    }

    public void setSet(RealmList<SetDTO> set) {
        this.set = set;
    }


}
