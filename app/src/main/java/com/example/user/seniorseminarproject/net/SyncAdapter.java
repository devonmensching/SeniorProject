package com.example.user.seniorseminarproject.net;


import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.seniorseminarproject.database.DBOperations;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This Sync Adapter class is where the data is transferred.
 * When the onPerformSync() method  encapsulates the data
 * transferred code. When the method is called the local Surveys
 * and Settings tables are converted to GSON objects and then
 * sent to the server.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    String url = "http://10.0.2.2:8080/SeniorProject/sync.php";
    ServerSync serverSync = new ServerSync(getContext());


    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        syncZoneData();
        getServerData();
    }

    private void syncZoneData() {

        RequestQueue queue = SingletonVolley.getInstance(getContext()).getRequestQueue();

        DBOperations uop = new DBOperations(getContext());
        final ArrayList<Object> tableList = uop.getZoneDataTableContents();

        String JSON;
        Gson gson = new Gson();
        JSON = gson.toJson(tableList); //Could take a while.

        Map<String, String> params = new HashMap<String, String>();
        params.put("syncMode", "CS");
        params.put("tableName", "ZoneData");
        params.put("username", "dmensching");
        params.put("tableRows", JSON);
        Log.d("OnPerformSync", "the request json is " + JSON);

        CustomVolleyGsonRequest req = new CustomVolleyGsonRequest(url, Object.class, params, new Response.Listener<Object>() {
            @Override
            public void onResponse(Object response) {

                if (response instanceof String) {
                    String result = (String) response;
                    Log.d("Volley", "Response:" + result);
                } else {
                    Log.i("OnPerformSync", "Yet to be implemented. Parse Server Response"); //TODO: Take care of this.
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Volley", "Error in getting the response");
                Log.e("Volley", error.getLocalizedMessage());

            }
        });
        SingletonVolley.getInstance(getContext()).addToRequestQueue(req);
    }

    private void getServerData() {
        RequestQueue queue = SingletonVolley.getInstance(getContext()).getRequestQueue();
        Map<String, String> params = new HashMap<String, String>();

        params.put("syncMode", "SC");
        params.put("username", "dmensching");

        CustomVolleyGsonRequest req = new CustomVolleyGsonRequest(url, Object.class, params, new Response.Listener<Object>() {
            @Override
            public void onResponse(Object response) {

                Log.d("getServerData", "Response received");

                Map<String, String> tableValues;
                try {
                    tableValues = (Map<String, String>) response;
                    for (Map.Entry<String, String> entry :
                            tableValues.entrySet()) {
                        String tableName = entry.getKey();
                        String values = entry.getValue();

                        JSONArray jArray = new JSONArray(values);
                        serverSync.insertRows(tableName, jArray);
                    }
                } catch (Exception e) {
                    Log.e("getServerData", e.getLocalizedMessage());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Volley","Error in getting the response");
                Log.e("Volley",error.getLocalizedMessage());

            }
        });

        SingletonVolley.getInstance(getContext()).addToRequestQueue(req);

    }

}