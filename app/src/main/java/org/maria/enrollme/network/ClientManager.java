package org.maria.enrollme.network;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.maria.enrollme.ClientEntity;
import org.maria.enrollme.MainActivity;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class ClientManager {
    EnrollServer server;
    private Gson gson = new Gson();
    final private org.maria.enrollme.MainActivity context;

    public ClientManager(EnrollServer a_server, org.maria.enrollme.MainActivity a_context) {
        server = a_server;
        context = a_context;
    }


    public void getAll() {
        Uri.Builder uri = new Uri.Builder();
        uri.scheme("http");
        uri.encodedAuthority(EnrollServer.base_url());
        uri.path("/api/clients/get");
        uri.appendQueryParameter("master_id", "1");
        String url = uri.build().toString();
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LinkedList<ClientEntity> clients = new LinkedList<ClientEntity>();
                Log.d(getClass().getName(), "sucess HTTP response " + response);
                Type type = new TypeToken<LinkedList<ClientEntity>>(){}.getType();
                clients = gson.fromJson(response, type);
                onNewData(clients);
            }
        };
        server.request(url, listener);
    }
    public void onNewData(LinkedList<ClientEntity> clients)
    {
        context.runOnUiThread(new Runnable()
        {
            @Override public void run()
            {
                context.updateFragments(clients);
            }
        });
    }
}
