package com.example.c54tpfinal;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GenericRequest {

    private JsonRequest jsonRequest;
    private String url;
    private JSONObject jsonObjectResponse;

    public GenericRequest(String url) {
        this.url = url;

        this.jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response); // For test in console
                        jsonObjectResponse = response;
                    }
                }, null)

            {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                Context sharedPreferences;
                String token = shPref.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };
    }

    public JSONObject getJsonObjectResponse() {
        return jsonObjectResponse;
    }
    
}
