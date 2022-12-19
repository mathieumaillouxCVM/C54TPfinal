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

    Ecouteur ec;
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

