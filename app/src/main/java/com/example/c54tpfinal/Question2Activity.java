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

public class Question2Activity extends AppCompatActivity {

    TextView question2Titre;
    ImageView drumSticks;
    Button btnAlbum1, btnAlbum2;
    SingletonVolley sg;
    ImageLoader imgLoader;
    NetworkImageView imgAlbum1, imgAlbum2;
    Vector<JSONObject> collectedResponse;
    Question2Urls q2Url;
    EcouteurQ2 ec2;
    int popularity1, popularity2, score;
    float wid, fromX, toX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);



    wid = Resources.getSystem().getDisplayMetrics().widthPixels;
    // Parce qu'intialement la view est -50dp en x
    fromX = -dpToPx(50);


    question2Titre = findViewById(R.id.question2Titre);
    imgAlbum1 = findViewById(R.id.imgAlbum1);
    imgAlbum2 = findViewById(R.id.imgAlbum2);
    btnAlbum1 = findViewById(R.id.btnAlbum1);
    btnAlbum2 = findViewById(R.id.btnAlbum2);
    drumSticks = findViewById(R.id.drumSticks);

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
        }
    }

    public void afficherQuestion2(Vector<JSONObject> collResp) {
        int lengthVec = q2Url.getVecUrlAlbums().size();
        int index1 = (int) (Math.random() * lengthVec);
        int index2;
        do {
            index2 = (int) (Math.random() * lengthVec);
        } while (index1 == index2);
        sg = SingletonVolley.getInstance(this);
        JSONArray imageArr0 = null;
        JSONArray imageArr1 = null;
        try {
            imageArr0 = collResp.get(index1).getJSONArray("images");
            imageArr1 = collResp.get(index2).getJSONArray("images");
            int indexArr0 = (int) (Math.random() * imageArr0.length());
            int indexArr1;
            do {
                indexArr1 = (int) (Math.random() * imageArr1.length());
            } while (indexArr0 == indexArr1);
            JSONObject imageObj0 = imageArr0.getJSONObject(indexArr0);
            JSONObject imageObj1 = imageArr1.getJSONObject(indexArr1);
            String imageUrl1 = imageObj0.getString("url");
            String imageUrl2 = imageObj1.getString("url");
            imgLoader = sg.getImageLoader();
            imgAlbum1.setImageUrl(imageUrl1, imgLoader);
            imgAlbum2.setImageUrl(imageUrl2, imgLoader);
            String artist1 = collResp.get(index1).getString("name");
            String artist2 = collResp.get(index2).getString("name");
            btnAlbum1.setText(artist1);
            btnAlbum2.setText(artist2);
            popularity1 = collResp.get(index1).getInt("popularity");
            popularity2 = collResp.get(index2).getInt("popularity");

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
        if ((v == btnAlbum1 && popularity1 > popularity2) || (v == btnAlbum2 && popularity1 < popularity2)) {
            Toast.makeText(Question2Activity.this, "Bravo", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(Question2Activity.this, "Oups", Toast.LENGTH_SHORT).show();
        }
        if (score < 3) {
            afficherQuestion2(collectedResponse);
            animateSticks();
        } else {
            Intent intent = new Intent(Question2Activity.this, Question3Activity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("FROM_X", fromX);
            intent.putExtra("TO_X", toX);
            startActivity(intent);
        }
    }
}
}