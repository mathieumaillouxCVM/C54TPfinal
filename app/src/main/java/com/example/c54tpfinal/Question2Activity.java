package com.example.c54tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.Collections;
import java.util.Vector;

public class Question2Activity extends AppCompatActivity {

    TextView albumTitre;
    ImageView shoes;
    Button btnDate1, btnDate2, btnDate3;
    ImageLoader imgLoader;
    NetworkImageView imgAlbum;
    Question2 question2;
    EcouteurQ2 ec2;
    String imgUrl, titre, answer, date2, date3;
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
        wid = b.getFloat("WID");

        imgAlbum = findViewById(R.id.imgAlbum);
        albumTitre = findViewById(R.id.albumTitre);
        btnDate1 = findViewById(R.id.btnDate1);
        btnDate2 = findViewById(R.id.btnDate2);
        btnDate3 = findViewById(R.id.btnDate3);
        shoes = findViewById(R.id.shoes);

        animateShoes();

        ec2 = new EcouteurQ2();

        btnDate1.setOnClickListener(ec2);
        btnDate2.setOnClickListener(ec2);
        btnDate3.setOnClickListener(ec2);

        btnDate1.setEnabled(false);
        btnDate2.setEnabled(false);
        btnDate3.setEnabled(false);

        question2 = new Question2(this);
    }

    public void activateBtns() {
        btnDate1.setEnabled(true);
        btnDate2.setEnabled(true);
        btnDate3.setEnabled(true);
    }

    public void animateShoes() {
        toX = (float)((score - 1) * (wid / 10));
        ObjectAnimator animX = ObjectAnimator.ofFloat(shoes, View.X, fromX, toX);
        ObjectAnimator animRotate = ObjectAnimator.ofFloat(shoes, View.ROTATION_X, 0f, 360f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animX, animRotate);
        fromX = toX;
        set.start();
    }

    public void afficherQuestion2(Vector<Object> data) {
            titre = (String)data.get(0);
            imgUrl = (String)data.get(1);
            answer = (String)data.get(2);
            date2 = (String)data.get(3);
            date3 = (String)data.get(4);

            albumTitre.setText(titre);

            imgLoader = SingletonVolley.getInstance(this).getImageLoader();
            imgAlbum.setImageUrl(imgUrl, imgLoader);

            Vector<String> shuffledVec = new Vector<>();
            shuffledVec.add(answer);
            shuffledVec.add(date2);
            shuffledVec.add(date3);
            Collections.shuffle(shuffledVec);

            btnDate1.setText(shuffledVec.get(0));
            btnDate2.setText(shuffledVec.get(1));
            btnDate3.setText(shuffledVec.get(2));
    }

    private class EcouteurQ2 implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Button b = (Button) view;
            if (b.getText().toString().equals(answer)) {
                Toast.makeText(Question2Activity.this, "Bravo", Toast.LENGTH_SHORT).show();
                score++;
            } else {
                Toast.makeText(Question2Activity.this, "Oups", Toast.LENGTH_SHORT).show();
            }
            if (score < 4) {
                question2.generateQuestion2();
                animateShoes();
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