package com.example.c54tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Vector;

public class  Question3Activity extends AppCompatActivity {

    AlbumUrls albumsUrls;
    Vector<JSONObject> collectedResponse;
    Button btn1, btn2, btn3;
    EcouteurQ3 ec3;
    int score;
    float fromX, toX, wid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3_oldy);

        Bundle b = getIntent().getExtras();
        score = b.getInt("SCORE");
        fromX = b.getFloat("FROM_X");
        toX = b.getFloat("TO_X");
        wid = b.getFloat("wid");

        ec3 = new EcouteurQ3();
        collectedResponse = new Vector<JSONObject>();

        albumsUrls = new AlbumUrls();
        Vector<String> vec;
        vec = albumsUrls.getVecUrlAlbums();
        // GenericRequest request = new GenericRequest(this, 3);
        // request.createRequest(vec.get(0));
    }

    public void getResponse(JSONObject response) {
        collectedResponse.add(response);
         System.out.println(response.toString());
        // if (collectedResponse.size() == albumsUrls.getVecUrlAlbums().size()) {
            afficherQuestion3(collectedResponse);
//            btn1.setOnClickListener(ec3);
//            btn2.setOnClickListener(ec3);
//            btn3.setOnClickListener(ec3);
        // }
    }

    public void afficherQuestion3(Vector<JSONObject> callResp) {
        // Sortir le nom d'une tracks sur différents albums de façon aléatoire
        // L'utilisateur doit choisir la track vient de quel album
        // ou quelle track est sur cet album.

        Gson gson = new GsonBuilder().create();
        Album album = gson.fromJson(String.valueOf(callResp.get(0)), Album.class);
        ListeTracks_old tracks = album.getTracks();
        Track_old track = tracks.get(0);
        Artist_old artist = album.getArtist();
        System.out.println(album.getRelease_date());
        System.out.println(track.getName());
        System.out.println(artist.getName());
    }

    private class EcouteurQ3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}


