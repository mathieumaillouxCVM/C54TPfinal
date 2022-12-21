package com.example.c54tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Collections;
import java.util.Vector;

public class  Question3Activity extends AppCompatActivity {

    ImageView shoes;
    Button btnAccueil;
    ImageLoader imgLoader;
    NetworkImageView imgAlbum;
    Question3 question3;
    EcouteurQ3 ec3;
    String imgUrl, answer, titre2, titre3;
    ListView liste;
    ArrayAdapter vectorAdapter;
    int score;
    float fromX, toX, wid, largeurBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        Bundle b = getIntent().getExtras();
        score = b.getInt("SCORE");
        fromX = b.getFloat("FROM_X");
        toX = b.getFloat("TO_X");
        wid = b.getFloat("WID");
        largeurBtn = (float)dpToPx(100);

        imgAlbum = findViewById(R.id.imgAlbum);
        liste = findViewById(R.id.liste);
        shoes = findViewById(R.id.shoes);
        // Utilsation d'une lambda parce que ce bouton apparaît uniquement lorsque le quiz est
        // terminé afin de retourner à l'accueil
        btnAccueil = findViewById(R.id.btnAccueil);
        btnAccueil.setOnClickListener( btnAccueil -> {
                Intent intent = new Intent(Question3Activity.this, MainActivity.class);
                startActivity(intent);
        });

        // On anime les "shoes" selon le score obtenu en terminant l'activité précédente
        animateShoes();

        ec3 = new EcouteurQ3();

        // Utilisation d'une liste pour le choix de réponse avec l'adapteur correspondant.
        liste.setOnItemClickListener((AdapterView.OnItemClickListener) ec3);
        liste.setEnabled(false);

        question3 = new Question3(this);

    }

    public void activateBtns() {
        liste.setEnabled(true);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void afficherQuestion3(Vector<Object> data) {
        imgUrl = (String)data.get(0);
        answer = (String)data.get(1);
        titre2 = (String)data.get(2);
        titre3 = (String)data.get(3);

        imgLoader = SingletonVolley.getInstance(this).getImageLoader();
        imgAlbum.setImageUrl(imgUrl, imgLoader);

        // On mélange les données envoyées par le modèle afin que la bonne réponse
        // ne se trouve pas toujours au même endroit dans la liste
        Vector<String> shuffledVec = new Vector<>();
        shuffledVec.add(answer);
        shuffledVec.add(titre2);
        shuffledVec.add(titre3);
        Collections.shuffle(shuffledVec);

        vectorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shuffledVec);
        liste.setAdapter(vectorAdapter);
    }

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

    // Animation spécifique lorsque le quiz est complété afin de faire appraître le bouton de retour
    // à l'accueil.
    public void animateEnd() {
        AnimatorSet set1 = new AnimatorSet();
        AnimatorSet set2 = new AnimatorSet();
        ObjectAnimator finish1 = ObjectAnimator.ofFloat(shoes, View.SCALE_X, 20);
        ObjectAnimator finish2 = ObjectAnimator.ofFloat(shoes, View.SCALE_Y, 20);
        ObjectAnimator finish3 = ObjectAnimator.ofFloat(shoes, View.ALPHA, 0);
        ObjectAnimator finish4 = ObjectAnimator.ofFloat(shoes, View.ROTATION_X,0f, 720f);
        ObjectAnimator finish5 = ObjectAnimator.ofFloat(shoes, View.ROTATION_Y,0f, 360f);
        ObjectAnimator accueil = ObjectAnimator.ofFloat(btnAccueil,View.X, wid, wid/2 - largeurBtn/2);
        set2.playTogether(finish1, finish2, finish3, finish4, finish5);
        set2.setDuration(5000);
        set1.playSequentially(set2, accueil);
        set1.setDuration(2000);
        set1.start();
    }

    private class EcouteurQ3 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (parent.getItemAtPosition(position).toString().equals(answer)) {
                Toast.makeText(Question3Activity.this, "Bravo", Toast.LENGTH_SHORT).show();
                score++;
                animateShoes();
            } else {
                Toast.makeText(Question3Activity.this, "Oups", Toast.LENGTH_SHORT).show();
            }
            if  (score < 6) {
                question3.generateQuestion3();
            } else {
                Toast.makeText(Question3Activity.this, "Vous avez complété le quiz!!!"
                        , Toast.LENGTH_SHORT).show();
                liste.setEnabled(false);
                animateEnd();
            }
        }
    }
}


