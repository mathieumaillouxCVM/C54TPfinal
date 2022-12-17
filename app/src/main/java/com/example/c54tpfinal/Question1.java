package com.example.c54tpfinal;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class Question1 {

    private final Vector<String> vecUrlArtists;
    private Context context;

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

        for (String url : vecUrlArtists) {
            GenericRequest request = new GenericRequest(context, 1);
            request.createRequest(url);
        }

}

    public void generateQuestion1() {
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
            imgArtist1.setImageUrl(imageUrl1, imgLoader);
            imgArtist2.setImageUrl(imageUrl2, imgLoader);
            String artist1 = collResp.get(index1).getString("name");
            String artist2 = collResp.get(index2).getString("name");
            btnArtist1.setText(artist1);
            btnArtist2.setText(artist2);
            popularity1 = collResp.get(index1).getInt("popularity");
            popularity2 = collResp.get(index2).getInt("popularity");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Vector<String> getVecUrlArtists() {
        return this.vecUrlArtists;
    }

}
