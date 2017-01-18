package dk.bepeaked.bodybook.Backend;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Nicki on 14/01/17.
 */

public class VolleyImageHelper {

    //This is for the packages Volley. It's a singleton, that controlls the download que of pictures.

private static VolleyImageHelper mInstance;
    private static Context mCtx;
    private RequestQueue requestQueue;

    private VolleyImageHelper(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleyImageHelper getInstance (Context context) {
        if (mInstance == null) {
            mInstance = new VolleyImageHelper(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQue (Request<T> request) {
        requestQueue.add(request);
    }
}
