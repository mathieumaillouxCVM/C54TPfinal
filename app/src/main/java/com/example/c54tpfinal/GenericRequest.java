package com.example.c54tpfinal;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GenericRequest{

    // Classe GenericRequest en singleton étant donné qu'une seule requête peut être faite
    // à la fois.  La méthode createRequest est appelé à l'instanciation de la

    private static GenericRequest instance;
    private Context context;

    private GenericRequest(Context context) {
        this.context = context;
    }

    public static GenericRequest getInstance(Context context) {
        if (instance == null) {
            instance = new GenericRequest(context);
        }
            return instance;
    }

    public void createRequest(String url, Object object) {
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        if (object instanceof Question1) {
                            ((Question1)object).getResponse(response);
                        } else if (object instanceof Question2) {
                            ((Question2) object).getResponse(response);
                        } else if (object instanceof Question3) {
                            ((Question3) object).getResponse(response);
                        }
                    }
                }, null) {
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
        SingletonVolley.getInstance(context).addToRequestQueue(jsonRequest);
    }
}
