package dk.bepeaked.bodybook.Backend;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Nicki on 14/01/17.
 */

public class VolleyImageHelper {

    //Alt dette er til Volley:
private static VolleyImageHelper mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private VolleyImageHelper(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleyImageHelper getInstance (Context context) {
        if (mInstance == null) {
            mInstance = new VolleyImageHelper(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQue (Request<T> request) {
        requestQueue.add(request);
    }
}
