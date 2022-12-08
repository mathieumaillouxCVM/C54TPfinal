package com.example.c54tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    TextView champTest;
    String urlPavement, urlNirvana, urlRadiohead, imageUrl;
    SingletonVolley sg;
    ImageLoader imgLoader;
    NetworkImageView img1, img2;
    JSONObject response;
    Vector<JSONObject> collectedResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                champTest = findViewById(R.id.champTest);
                img1 = findViewById(R.id.img1);
                img2= findViewById(R.id.img2);

                urlPavement = "https://api.spotify.com/v1/artists/3inCNiUr4R6XQ3W43s9Aqi";
                urlNirvana = "https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb";
                urlRadiohead = "https://api.spotify.com/v1/artists/6olE6TJLqED3rqDCT0FyPh";

                collectedResponse = new Vector<JSONObject>();

                GenericRequest requestPavement = new GenericRequest(this);
                requestPavement.createRequest(urlPavement);
                GenericRequest requestNirvana = new GenericRequest(this);
                requestNirvana.createRequest(urlNirvana);
                GenericRequest requestRadiohead = new GenericRequest(this);
                requestRadiohead.createRequest(urlRadiohead);



    }
    public void getResponse (JSONObject response) {
        collectedResponse.add(response);
        System.out.println(response.toString() + "\n");
        if (collectedResponse.size() == 3) {
            afficherImages(collectedResponse);
        }
    }

    public void afficherImages (Vector<JSONObject> collResp) {
        sg = SingletonVolley.getInstance(this);
        JSONArray imageArr0 = null;
        JSONArray imageArr1 = null;
        try {
            imageArr0 = collResp.get(0).getJSONArray("images");
            imageArr1 = collResp.get(1).getJSONArray("images");
            JSONObject imageObj0 = imageArr0.getJSONObject(0);
            JSONObject imageObj1 = imageArr1.getJSONObject(0);
            String imageUrl1 = imageObj0.getString("url");
            String imageUrl2 = imageObj1.getString("url");
            imgLoader = sg.getImageLoader();
            img1.setImageUrl(imageUrl1, imgLoader);
            img2.setImageUrl(imageUrl2, imgLoader);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


//        SharedPreferences shPref = getApplicationContext().getSharedPreferences("SPOTIFY", 0);


//        sg = SingletonVolley.getInstance(this);
//        imgLoader = sg.getImageLoader();
//
//        imageTest.setImageUrl(imageUrl, imgLoader);
//
//        JSONArray imageArr = null;
//        try {
//            imageArr = response.getJSONArray("images");
//            JSONObject imageObj = imageArr.getJSONObject(0);
//            imageUrl = imageObj.getString("url");
//           // int width = imageObj.getInt("width");
//           // int height = imageObj.getInt("height");
//            imageTest.setImageUrl(imageUrl, imgLoader);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }




//        jsonRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                urlPavement,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        System.out.println(response);
//                        try {
//                            JSONArray imageArr = response.getJSONArray("images");
//                            JSONObject imageObj = imageArr.getJSONObject(0);
//                            imageUrl = imageObj.getString("url");
//
//
//                            int width = imageObj.getInt("width");
//                            int height = imageObj.getInt("height");
//
//
//                            imageTest.setImageUrl(imageUrl, imgLoader);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
////                          // JSONArray imagesArr = response.getJSONArray("images");
//                    }
//                }, null)
//                {
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        Map<String, String> headers = new HashMap<>();
//                        Context sharedPreferences;
//                        String token = shPref.getString("token", "");
//                        String auth = "Bearer " + token;
//                        headers.put("Authorization", auth);
//                        headers.put("Content-Type", "application/json; charset=utf-8");
//                        return headers;
//                    }
//                };
