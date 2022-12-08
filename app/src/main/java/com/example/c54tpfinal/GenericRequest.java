package com.example.c54tpfinal;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
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

public class GenericRequest{

    private JsonRequest jsonRequest;
    private JSONObject jsonObjectResponse;
    private Context context;

    public GenericRequest(Context context) {
        this.context = context;
    }

    public void createRequest(String url) {
        this.jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)  {
                            ((MainActivity)context).getResponse(response);
//                        try {
//                            String name = response.getString("name");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(response); // For test in console
//                        jsonObjectResponse = response;
                    }
                }, null)

            {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                SharedPreferences shPref = context.getSharedPreferences("SPOTIFY", 0);
                String token = shPref.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };
        SingletonVolley.getInstance(context).addToRequestQueue(this.jsonRequest);
    }


//
//    public JsonRequest getJsonRequest() {
//        return jsonRequest;
//    }
//
//    public JSONObject getJsonObjectResponse() {
//        return jsonObjectResponse;
//    }

}
