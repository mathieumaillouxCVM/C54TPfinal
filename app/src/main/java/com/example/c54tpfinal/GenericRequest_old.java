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

public class GenericRequest_old {

    // Créer les objets des questions ici afin des les avoir accessibles pour les
    // pour le retour des infos sinon mieux, passer l'objet en argument dans
    // la méthode createRequest


    //  private JSONObject jsonObjectResponse;
    private Context context;

    public GenericRequest_old(Context context) {
        this.context = context;
    }

    public void createRequest(String url, Object object) {
        // System.out.println(response);
        /*else if (object instanceof Question2) {
                            ((Question2Activity)context).getResponse(response);
                        }
                        else if (object instanceof Question3) {
                            ((Question3Activity)context).getResponse(response);
                        }*/
        //                        try {
        //                            String name = response.getString("name");
        //                        } catch (JSONException e) {
        //                            e.printStackTrace();
        //                        }
        //                        System.out.println(response); // For test in console
        //                        jsonObjectResponse = response;
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // System.out.println(response);
                        if (object instanceof Question1) {
                            ((Question1) object).getResponse(response);

                        }
                        /*else if (object instanceof Question2) {
                            ((Question2Activity)context).getResponse(response);
                        }
                        else if (object instanceof Question3) {
                            ((Question3Activity)context).getResponse(response);
                        }*/
//                        try {
//                            String name = response.getString("name");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(response); // For test in console
//                        jsonObjectResponse = response;
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


//
//    public JsonRequest getJsonRequest() {
//        return jsonRequest;
//    }
//
//    public JSONObject getJsonObjectResponse() {
//        return jsonObjectResponse;
//    }

}
