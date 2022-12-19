package com.example.c54tpfinal;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class Question1 {

    private Context context;
    private final Vector<String> vecUrlArtists;
    private Vector<JSONObject> collectedResponse;

    public Question1(Context context) {
        this.context = context;
        vecUrlArtists = new Vector<>();
        vecUrlArtists.add("https://api.spotify.com/v1/artists/3inCNiUr4R6XQ3W43s9Aqi");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/6olE6TJLqED3rqDCT0FyPh");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/40Yq4vzPs9VNUrIBG5Jr2i");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/64tNsm6TnZe2zpcMVMOoHL");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/1w5Kfo2jwwIPruYS2UWh56");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/2UazAtjfzqBF0Nho2awK4z");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/5xUf6j4upBrXZPg6AI4MRK");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/5SHQUMAmEK5KmuSb0aDvsn");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/2zMQOJ4Cyl4BYbw6WqaO3h");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/267VY6GX5LyU5c9M85ECZQ");
        vecUrlArtists.add("https://api.spotify.com/v1/artists/1xgFexIwrf2QjbU0buCNnp");
        collectedResponse = new Vector<JSONObject>();
        for (String url : vecUrlArtists) {
            GenericRequest request = GenericRequest.getInstance(context);
            request.createRequest(url, this);
        }
    }

    public void generateQuestion1() {
        Vector<Object> data = new Vector<>();
        int sizeVec = vecUrlArtists.size();
        int index1 = (int) (Math.random() * sizeVec);
        int index2;
        do {
            index2 = (int) (Math.random() * sizeVec);
        } while (index1 == index2);
        SingletonVolley sg = SingletonVolley.getInstance((Question1Activity)context);
        JSONArray imageArr0 = null;
        JSONArray imageArr1 = null;
        try {
            imageArr0 = collectedResponse.get(index1).getJSONArray("images");
            imageArr1 = collectedResponse.get(index2).getJSONArray("images");
//            int indexArr0 = (int) (Math.random() * imageArr0.length());
//            int indexArr1;
//            do {
//                indexArr1 = (int) (Math.random() * imageArr1.length());
//            } while (indexArr0 == indexArr1);
            JSONObject imageObj0 = imageArr0.getJSONObject(0);
            JSONObject imageObj1 = imageArr1.getJSONObject(0);
            String imageUrl1 = imageObj0.getString("url");
            String imageUrl2 = imageObj1.getString("url");

            // Partie à laisser dans la vue dans une méthode afficherQuestion


//            imgLoader = sg.getImageLoader();
//            imgArtist1.setImageUrl(imageUrl1, imgLoader);
//            imgArtist2.setImageUrl(imageUrl2, imgLoader);
            String artist1 = collectedResponse.get(index1).getString("name");
            String artist2 = collectedResponse.get(index2).getString("name");
//            btnArtist1.setText(artist1);
//            btnArtist2.setText(artist2);
            int popularity1 = collectedResponse.get(index1).getInt("popularity");
            int popularity2 = collectedResponse.get(index2).getInt("popularity");
            data.add(imageUrl1);
            data.add(imageUrl2);
            data.add(artist1);
            data.add(artist2);
            data.add(popularity1);
            data.add(popularity2);
            ((Question1Activity)context).afficherQuestion1(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void getResponse(JSONObject response) {
        collectedResponse.add(response);
        System.out.println(response.toString());
        if (collectedResponse.size() == vecUrlArtists.size()) {
            Question1Activity question1Activity = (Question1Activity) context;
            question1Activity.activateBtns();
            generateQuestion1();
        }
    }
}
