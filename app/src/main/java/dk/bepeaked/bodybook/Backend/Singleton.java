package dk.bepeaked.bodybook.Backend;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Nicki on 13/01/17.
 */

public class Singleton extends Application {

    //This is the application object (singleton)

   public static Singleton singleton;

    public int antalDialoger;
    ArrayList<Runnable> observators = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;

    }

    public void notifyObservers() {
        for (Runnable r : observators ) {
            r.run();
        }
    }
    public void listen (Runnable r) {
        observators.add(r);
    }

    public void unRegistrer (Runnable r) {
        observators.remove(r);
    }


}
