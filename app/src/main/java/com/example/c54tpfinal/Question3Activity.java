package com.example.c54tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONObject;

import java.util.Vector;

public class Question3Activity extends AppCompatActivity {

    Vector<JSONObject> collectedResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        collectedResponse = new Vector<JSONObject>();

    }
    public void getResponse(JSONObject response) {
        collectedResponse.add(response);
        // System.out.println(response.toString() + "\n");
//        if (collectedResponse.size() == q3Url.getvecUrlAlbums().size()) {
//            afficherQuestion1(collectedResponse);
//            btnAlbum1.setOnClickListener(ec);
//            btnAlbum2.setOnClickListener(ec);
    }
}


