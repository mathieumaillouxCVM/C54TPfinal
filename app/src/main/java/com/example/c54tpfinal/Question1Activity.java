package com.example.c54tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
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

import java.util.Vector;

public class Question1Activity extends AppCompatActivity {

    TextView question1Titre;
    ImageView shoes;
    Button btnArtist1, btnArtist2;
    ImageLoader imgLoader;
    NetworkImageView imgArtist1, imgArtist2;
    Question1 question1;
    EcouteurQ1 ec1;
    String imageUrl1, imageUrl2, artist1, artist2;
    int popularity1, popularity2, score;
    float wid, fromX, toX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        score = 0;
        wid = Resources.getSystem().getDisplayMetrics().widthPixels;
        // Parce qu'intialement la view est -50dp en x
        fromX = -dpToPx(50);

        question1Titre = findViewById(R.id.question1Titre);
        imgArtist1 = findViewById(R.id.imgArtist1);
        imgArtist2 = findViewById(R.id.imgArtist2);
        btnArtist1 = findViewById(R.id.btnArtist1);
        btnArtist2 = findViewById(R.id.btnArtist2);
        shoes = findViewById(R.id.shoes);

        ec1 = new EcouteurQ1();

        btnArtist1.setOnClickListener(ec1);
        btnArtist2.setOnClickListener(ec1);

        // Les boutons sont désactivés jusqu'à ce que l'ensemble des requêtes soient
        // complétés afin de bâtir les questions.
        btnArtist1.setEnabled(false);
        btnArtist2.setEnabled(false);

        // Objet question1 instancié (modèle)
        question1 = new Question1(this);
    }

    public void activateBtns() {
        btnArtist1.setEnabled(true);
        btnArtist2.setEnabled(true);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    // Méthode appelée lorsqu'une bonne réponse est donnée
    public void animateShoes() {
        toX = (float)((score - 1) * (wid / 10));
        ObjectAnimator animX = ObjectAnimator.ofFloat(shoes, View.X, fromX, toX);
        ObjectAnimator animRotate = ObjectAnimator.ofFloat(shoes, View.ROTATION, 0f, 360f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(animX, animRotate);
        fromX = toX;
        set.start();
    }

    public void afficherQuestion1(Vector<Object> data) {
        imageUrl1 = (String)data.get(0);
        imageUrl2 = (String)data.get(1);
        artist1 = (String)data.get(2);
        artist2 = (String)data.get(3);
        popularity1 = (Integer) data.get(4);
        popularity2 = (Integer)data.get(5);

        imgLoader = SingletonVolley.getInstance(this).getImageLoader();
        imgArtist1.setImageUrl(imageUrl1, imgLoader);
        imgArtist2.setImageUrl(imageUrl2, imgLoader);

        btnArtist1.setText(artist1);
        btnArtist2.setText(artist2);
    }

    private class EcouteurQ1 implements View.OnClickListener {
        // Vérification de la réponse donnée par l'utilisateur à la question.  Plus simple de référer
        // directement sur les données récupérées ici (en partie pour l'affichage)
        // que de les laisser dans le modèle, du moins pour la question présente (popularité).

        @Override
        public void onClick(View v) {
            if ((v == btnArtist1 && popularity1 > popularity2) || (v == btnArtist2 && popularity1 < popularity2)) {
                Toast.makeText(Question1Activity.this, "Bravo", Toast.LENGTH_SHORT).show();
                score++;
                animateShoes();
            } else {
                Toast.makeText(Question1Activity.this, "Oups", Toast.LENGTH_SHORT).show();
            }
            if (score < 2) {
                question1.generateQuestion1();
            } else {
                // Lorsque 2 bonnes réponses ont été données, la position des "shoes" de l'animation
                // est passée dans l'extra de l'intent avec le score pour qu'ils soient au bon endroit
                // dans la prochaine activité.  L'animation pour la bonne réponse aura lieu dans la
                // prochaine activité.
                Intent intent = new Intent(Question1Activity.this, Question2Activity.class);
                intent.putExtra("SCORE", score);
                intent.putExtra("FROM_X", fromX);
                intent.putExtra("TO_X", toX);
                intent.putExtra("WID", wid);
                startActivity(intent);
            }
        }
    }
}