package com.example.c54tpfinal;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Vector;

public class Question1 {

    private Context context;
    private Vector<JSONObject> collectedResponse;
    private final Vector<String> vecUrlArtists;
    private Vector<Integer> indexArtists;
    private Gson gson;

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
        indexArtists = new Vector<>();
        remplirIndex();
    }

    public void remplirIndex() {
        for (int i = 0; i < vecUrlArtists.size(); i++){
            indexArtists.add(i);
        }
        Collections.shuffle(vecUrlArtists);
    }

    public void generateQuestion1() {
        Vector<Object> data = new Vector<>();
        if (indexArtists.size() == 0) {
            remplirIndex();
        }
        int index1 = indexArtists.remove(0);
        int index2 = indexArtists.remove(0);

        gson = new GsonBuilder().create();
        GSONArtist artist1 = gson.fromJson(String.valueOf(collectedResponse.get(index1)), GSONArtist.class);
        GSONArtist artist2 = gson.fromJson(String.valueOf(collectedResponse.get(index2)), GSONArtist.class);
        String imageUrl1 = artist1.getImages(0).getUrl();
        String imageUrl2 = artist2.getImages(0).getUrl();
        String name1 = artist1.name;
        String name2 = artist2.name;
        int popularity1 = artist1.popularity;
        int popularity2 = artist2.popularity;
//        SingletonVolley sg = SingletonVolley.getInstance((Question1Activity)context);
//        JSONArray imageArr0 = null;
//        JSONArray imageArr1 = null;
//        try {
//            imageArr0 = collectedResponse.get(index1).getJSONArray("images");
//            imageArr1 = collectedResponse.get(index2).getJSONArray("images");
//            JSONObject imageObj0 = imageArr0.getJSONObject(0);
//            JSONObject imageObj1 = imageArr1.getJSONObject(0);
//            String imageUrl1 = imageObj0.getString("url");
//            String imageUrl2 = imageObj1.getString("url");
//            String artist1 = collectedResponse.get(index1).getString("name");
//            String artist2 = collectedResponse.get(index2).getString("name");
//            int popularity1 = collectedResponse.get(index1).getInt("popularity");
//            int popularity2 = collectedResponse.get(index2).getInt("popularity");
            data.add(imageUrl1);
            data.add(imageUrl2);
            data.add(name1);
            data.add(name2);
            data.add(popularity1);
            data.add(popularity2);
            ((Question1Activity)context).afficherQuestion1(data);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
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
