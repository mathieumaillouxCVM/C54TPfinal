package com.example.c54tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView testInfo;
    String url;
    SingletonVolley sg;
    JsonObjectRequest jsonRequest;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testInfo = findViewById(R.id.testInfo);
        sg = SingletonVolley.getInstance(MainActivity.this);
        SharedPreferences shPref = getApplicationContext().getSharedPreferences("SPOTIFY", 0);

        url = "https://open.spotify.com/artist/3inCNiUr4R6XQ3W43s9Aqi?si=ZysWldN2S2WtSqn00pBP6g";

        jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("artist");
                            testInfo.setText(array.getString(0));
                        } catch (JSONException jsonException)
                        {
                            jsonException.printStackTrace();
                        }
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


        sg.addToRequestQueue(jsonRequest);
    }
}
