package dk.bepeaked.bodybook.Backend;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Nicki on 13/01/17.
 */

public class Singleton extends Application {

   public static Singleton singleton;

    public int antalDialoger = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("nicki", "Singleton.onCreate: ");
        this.singleton = this;

    }

    ArrayList<Runnable> observators = new ArrayList<>();

    public void notifyObservers() {
        Log.d("Nicki", "notifyObservers bliver kørt: listen er "+observators.size());
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
