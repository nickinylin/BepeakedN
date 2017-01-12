package dk.bepeaked.bodybook.Backend.DAO;

import java.util.Set;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by lukas on 06-01-2017.
 */

public class SetDAO {


    Realm realm = Realm.getDefaultInstance();

    /**
     * Add a set to an exercise
     * @param exerciseName The exercise which you want to add the set to
     * @param newSet The new set you want to add
     */
    public void addSet(String exerciseName, SetDTO newSet){

        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("name", exerciseName).findFirst();

        realm.beginTransaction();
        realmExercise.getSets().add(newSet);
        realm.commitTransaction();
    }

    /**
     * Deletes a set from an exercise
     * @param id the id of the set to be deleted
     */
    public void deleteSet(int id){

        SetDTO set = realm.where(SetDTO.class).equalTo("id", id).findFirst();

        realm.beginTransaction();
        set.deleteFromRealm();
        realm.commitTransaction();
    }

    public RealmList<SetDTO> getSets(String exersiceName){

        RealmResults<SetDTO> resultSets = realm.where(SetDTO.class).equalTo("exerciseName", exersiceName).findAll();
        RealmList<SetDTO> listSets = new RealmList<>();
        listSets.addAll(resultSets.subList(0, resultSets.size()));

        return listSets;
    }

    public RealmList<SetDTO> getAllSets() throws NullPointerException{
        RealmResults<SetDTO> resultSets = realm.where(SetDTO.class).findAll();
        RealmList<SetDTO> listSets = new RealmList<>();
        listSets.addAll(resultSets.subList(0, resultSets.size()));
        return listSets;
    }
}
