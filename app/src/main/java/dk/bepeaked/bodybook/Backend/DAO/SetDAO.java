package dk.bepeaked.bodybook.Backend.DAO;

import dk.bepeaked.bodybook.Backend.DTO.ExerciseDTO;
import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import io.realm.Realm;
import io.realm.RealmList;

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
        realmExercise.getSet().add(newSet);
        realm.commitTransaction();
    }

    /**
     * Deletes a set from an exercise
     * @param exerciseName the name of the exercise
     * @param setDeleted the set object to be deleted
     */
    public void deleteSet(String exerciseName, SetDTO setDeleted){

        ExerciseDTO realmExercise = realm.where(ExerciseDTO.class).equalTo("name", exerciseName).findFirst();

        realm.beginTransaction();
        realmExercise.deleteFromRealm();
        realm.commitTransaction();
    }

//    public RealmList<SetDTO> getSets(String exersiceName){
//
//    }
}
