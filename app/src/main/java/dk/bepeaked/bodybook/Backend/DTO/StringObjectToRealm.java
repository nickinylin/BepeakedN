package dk.bepeaked.bodybook.Backend.DTO;

import io.realm.RealmObject;

/**
 * Created by Nicki on 06/01/17.
 */

public class StringObjectToRealm extends RealmObject {

    String string;

    public StringObjectToRealm () {}

    public StringObjectToRealm(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
