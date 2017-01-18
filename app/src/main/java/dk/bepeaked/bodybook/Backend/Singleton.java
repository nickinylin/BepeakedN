package dk.bepeaked.bodybook.Backend;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by Nicki on 13/01/17.
 */

public class Singleton extends Application {

    //This is the application object (singleton)

   public static Singleton singleton;

    public int antalDialoger = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        this.singleton = this;

    }

    ArrayList<Runnable> observators = new ArrayList<>();

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
