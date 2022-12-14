package com.example.c54tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    //    TextView question1Titre;
//    ImageView drumSticks;
//    Button btnArtist1, btnArtist2;
//    SingletonVolley sg;
//    ImageLoader imgLoader;
//    NetworkImageView img1, img2;
//    Vector<JSONObject> collectedResponse;
//    Question1Urls q1Url;
    Ecouteur ec;
//    int popularity1, popularity2, score;
//    float wid, fromX, toX;
    Button btnDemarrer;
    int score;
    float fromX, toX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = 0;
        fromX = 0;
        toX = 0;

        btnDemarrer = findViewById(R.id.btnDemarrer);

        ec = new Ecouteur();

        btnDemarrer.setOnClickListener(ec);

//        btnDemarrer.setOnClickListener((btn) -> {
//            Intent intent = new Intent(MainActivity.this, Question1Activity.class);
//            intent.putExtra("SCORE", score);
//            intent.putExtra("FROM_X", fromX);
//            intent.putExtra("TO_X", toX);
//            startActivity(intent);
//        });


//        wid = Resources.getSystem().getDisplayMetrics().widthPixels;
//        // Parce qu'intialement la view est -50dp en x
//        fromX = -dpToPx(50);
//
//
//        question1Titre = findViewById(R.id.question1Titre);
//        img1 = findViewById(R.id.img1);
//        img2 = findViewById(R.id.img2);
//        btnArtist1 = findViewById(R.id.btnArtist1);
//        btnArtist2 = findViewById(R.id.btnArtist2);
//        drumSticks = findViewById(R.id.drumSticks);
//
//        ec = new Ecouteur();
//        collectedResponse = new Vector<JSONObject>();
//
//        q1Url = new Question1Urls();
//        Vector<String> vec;
//        vec = q1Url.getVecUrlArtists();
//        for (String url : vec) {
//            GenericRequest request = new GenericRequest(this);
//            request.createRequest(url);
//        }
//    }
//
//    public void getResponse(JSONObject response) {
//        collectedResponse.add(response);
//        // System.out.println(response.toString() + "\n");
//        if (collectedResponse.size() == q1Url.getVecUrlArtists().size()) {
//            afficherQuestion1(collectedResponse);
//            btnArtist1.setOnClickListener(ec);
//            btnArtist2.setOnClickListener(ec);
//        }
//    }
//
//    public void afficherQuestion1(Vector<JSONObject> collResp) {
//        int lengthVec = q1Url.getVecUrlArtists().size();
//        int index1 = (int) (Math.random() * lengthVec);
//        int index2;
//        do {
//            index2 = (int) (Math.random() * lengthVec);
//        } while (index1 == index2);
//        sg = SingletonVolley.getInstance(this);
//        JSONArray imageArr0 = null;
//        JSONArray imageArr1 = null;
//        try {
//            imageArr0 = collResp.get(index1).getJSONArray("images");
//            imageArr1 = collResp.get(index2).getJSONArray("images");
//            int indexArr0 = (int) (Math.random() * imageArr0.length());
//            int indexArr1 = (int) (Math.random() * imageArr1.length());
//            JSONObject imageObj0 = imageArr0.getJSONObject(indexArr0);
//            JSONObject imageObj1 = imageArr1.getJSONObject(indexArr1);
//            String imageUrl1 = imageObj0.getString("url");
//            String imageUrl2 = imageObj1.getString("url");
//            imgLoader = sg.getImageLoader();
//            img1.setImageUrl(imageUrl1, imgLoader);
//            img2.setImageUrl(imageUrl2, imgLoader);
//            String artist1 = collResp.get(index1).getString("name");
//            String artist2 = collResp.get(index2).getString("name");
//            btnArtist1.setText(artist1);
//            btnArtist2.setText(artist2);
//            popularity1 = collResp.get(index1).getInt("popularity");
//            popularity2 = collResp.get(index2).getInt("popularity");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static int dpToPx(int dp) {
//        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
//    }
//
//    public void animateSticks() {
//        toX = (float)((score - 1) * (wid / 10));
//        ObjectAnimator anim = ObjectAnimator.ofFloat(drumSticks, View.X, fromX, toX);
//        fromX = toX;
//        anim.start();
//    }
//
//    private class Ecouteur implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            if ((v == btnArtist1 && popularity1 > popularity2) || (v == btnArtist2 && popularity1 < popularity2)) {
//                Toast.makeText(MainActivity.this, "Bravo", Toast.LENGTH_SHORT).show();
//                score++;
//            } else {
//                Toast.makeText(MainActivity.this, "Oups", Toast.LENGTH_SHORT).show();
//            }
//            if (score < 3) {
//                afficherQuestion1(collectedResponse);
//                animateSticks();
//            } else {
//                Intent intent = new Intent(MainActivity.this, Question2Activity.class);
//                intent.putExtra("SCORE", score);
//                intent.putExtra("FROM_X", fromX);
//                intent.putExtra("TO_X", toX);
//                startActivity(intent);
//            }
//        }
//    }
    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Question1Activity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("FROM_X", fromX);
            intent.putExtra("TO_X", toX);
            startActivity(intent);

        }
    }
}

