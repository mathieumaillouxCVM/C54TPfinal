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

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Question2Activity extends AppCompatActivity {

    TextView question2Titre;
    ImageView drumSticks;
    Button btnAlbum1, btnAlbum2, btnAlbum3;
    SingletonVolley sg;
    ImageLoader imgLoader;
    NetworkImageView imgAlbum;
    Vector<JSONObject> collectedResponse;
    Question2Urls q2Url;
    EcouteurQ2 ec2;
    String answer;
    int score;
    float wid, fromX, toX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        Bundle b = getIntent().getExtras();
        score = b.getInt("SCORE");
        fromX = b.getFloat("FROM_X");
        toX = b.getFloat("TO_X");
        wid = b.getFloat("wid");


        question2Titre = findViewById(R.id.question2Titre);
        imgAlbum = findViewById(R.id.imgAlbum);
        btnAlbum1 = findViewById(R.id.btnAlbum1);
        btnAlbum2 = findViewById(R.id.btnAlbum2);
        btnAlbum3 = findViewById(R.id.btnAlbum3);
        drumSticks = findViewById(R.id.drumSticks);

        animateSticks();

        ec2 = new EcouteurQ2();
        collectedResponse = new Vector<JSONObject>();

        q2Url = new Question2Urls();
        Vector<String> vec;
        vec = q2Url.getVecUrlAlbums();
            for (String url : vec) {
            GenericRequest request = new GenericRequest(this, 2);
            request.createRequest(url);
        }
    }

    public void getResponse(JSONObject response) {
        collectedResponse.add(response);
        // System.out.println(response.toString() + "\n");
        if (collectedResponse.size() == q2Url.getVecUrlAlbums().size()) {
            afficherQuestion2(collectedResponse);
            btnAlbum1.setOnClickListener(ec2);
            btnAlbum2.setOnClickListener(ec2);
            btnAlbum3.setOnClickListener(ec2);
        }
    }

    public void afficherQuestion2(Vector<JSONObject> collResp) {
        int artistIndex = (int) (Math.random() * q2Url.getVecUrlAlbums().size());
        sg = SingletonVolley.getInstance(this);
        JSONArray imageArr = null;
        try {
            JSONArray albumArray = collResp.get(artistIndex).getJSONArray("items");
            Vector<Integer> albumIndex = new Vector<>();
            for (int i = 0; i < albumArray.length(); i++) {
                albumIndex.add(i);
            }
            Collections.shuffle(albumIndex);
            JSONObject albumAnswer = albumArray.getJSONObject(albumIndex.get(0));
            JSONObject album1 = albumArray.getJSONObject(albumIndex.get(1));
            JSONObject album2 = albumArray.getJSONObject(albumIndex.get(2));

            imageArr = albumAnswer.getJSONArray("images");
            JSONObject imageJObj = imageArr.getJSONObject(0);
            String imageUrl = imageJObj.getString("url");
            imgLoader = sg.getImageLoader();
            imgAlbum.setImageUrl(imageUrl, imgLoader);

            String answerDate = albumAnswer.getString("release_date");
            answer = answerDate;
            String album1Date = album1.getString("release_date");
            String album2Date = album2.getString("release_date");

            Vector<String> answersStr = new Vector<>();
            answersStr.add(answerDate);
            answersStr.add(album1Date);
            answersStr.add(album2Date);

            Collections.shuffle(answersStr);

            btnAlbum1.setText(answersStr.get(0));
            btnAlbum2.setText(answersStr.get(1));
            btnAlbum3.setText(answersStr.get(2));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void animateSticks() {
        toX = (float)((score - 1) * (wid / 10));
        ObjectAnimator anim = ObjectAnimator.ofFloat(drumSticks, View.X, fromX, toX);
        fromX = toX;
        anim.start();
    }

    private class EcouteurQ2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            if (b.getText().toString().equals(answer)) {
                Toast.makeText(Question2Activity.this, "Bravo", Toast.LENGTH_SHORT).show();
                score++;
            } else {
                Toast.makeText(Question2Activity.this, "Oups", Toast.LENGTH_SHORT).show();
            }

            if (score < 6) {
                afficherQuestion2(collectedResponse);
                animateSticks();
            } else {
                Intent intent = new Intent(Question2Activity.this, Question3Activity.class);
                intent.putExtra("SCORE", score);
                intent.putExtra("FROM_X", fromX);
                intent.putExtra("TO_X", toX);
                intent.putExtra("WID", wid);
                startActivity(intent);
            }
        }
    }
}