package dk.bepeaked.bodybook.Backend.DAO;

import dk.bepeaked.bodybook.Backend.DTO.SetDTO;
import io.realm.Realm;

/**
 * Created by lukas on 04-01-2017.
 */

public class SetDAO {

    Realm realm = Realm.getDefaultInstance();

    public void newSet(SetDTO newSet){
        realm.beginTransaction();
        SetDTO realmSet = realm.copyToRealm(newSet);
        realm.commitTransaction();
    }


}
