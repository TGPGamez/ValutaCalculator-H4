package com.tgpgamez.valutacalculator_h4.Logic;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class ApiHandler {
    /**
     * key and url path for authorization to use the api
     * It could be made more dynamic.
     * */
    String key = "4ca50ff9af0d8a78a7c837fc";
    String path = String.format("https://v6.exchangerate-api.com/v6/%s/", key);

    /**
     * Queue to request HTTP call
     */
    private RequestQueue queue;

    public ApiHandler(RequestQueue queue) {
        this.queue = queue;
    }

    /**
     * Method to request a json object
     * @param urlExtra Extra parameters that needs to be added to path
     * @param listener
     */
    public void requestJSONObject(String urlExtra, Response.Listener<JSONObject> listener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(path + urlExtra, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);
    }
}
