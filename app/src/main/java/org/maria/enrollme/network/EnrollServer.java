package org.maria.enrollme.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.*;
import com.android.volley.toolbox.*;

public class EnrollServer {

    final private Context context;

    public EnrollServer(Context a_context) {
        context = a_context;
    }

    public static String base_url() {
        return "192.168.1.41:8888";
    }

    public void request(String url, Response.Listener<String> listener) {

        Log.d(getClass().getName(), "HTTP request " + url);
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(getClass().getName(), "HTTP response " + error);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
