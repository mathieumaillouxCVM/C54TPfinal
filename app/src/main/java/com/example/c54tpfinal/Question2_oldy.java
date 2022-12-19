package com.example.c54tpfinal;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Vector;

public class Question2_oldy {

    private Context context;
    private final Vector<String> vecUrlAlbums;
    private Vector<JSONObject> collectedResponse;

    public Question2_oldy(Context context) {
        this.context = context;
        vecUrlAlbums = new Vector<>();
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/3inCNiUr4R6XQ3W43s9Aqi/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/6olE6TJLqED3rqDCT0FyPh/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/40Yq4vzPs9VNUrIBG5Jr2i/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/64tNsm6TnZe2zpcMVMOoHL/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/1w5Kfo2jwwIPruYS2UWh56/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/2UazAtjfzqBF0Nho2awK4z/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/5xUf6j4upBrXZPg6AI4MRK/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/5SHQUMAmEK5KmuSb0aDvsn/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/2zMQOJ4Cyl4BYbw6WqaO3h/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/267VY6GX5LyU5c9M85ECZQ/albums");
        collectedResponse = new Vector<JSONObject>();
        for (String url : vecUrlAlbums) {
            GenericRequest request = GenericRequest.getInstance(context);
            request.createRequest(url, this);
        }
    }

    public void generateQuestion2() {
        Vector<Object> data = new Vector<>();
        int randomArtist = (int) (Math.random() * vecUrlAlbums.size());

        try {
            JSONArray albumArray = collectedResponse.get(randomArtist).getJSONArray("items");
            Vector<Integer> albumIndex = new Vector<>();
            for (int i = 0; i < albumArray.length(); i++) {
                albumIndex.add(i);
            }

            Collections.shuffle(albumIndex);
            JSONObject albumAnswer = albumArray.getJSONObject(albumIndex.get(0));
            JSONObject album1 = albumArray.getJSONObject(albumIndex.get(1));
            JSONObject album2 = albumArray.getJSONObject(albumIndex.get(2));
            JSONArray imageArr = albumAnswer.getJSONArray("images");
            JSONObject imageJObj = imageArr.getJSONObject(0);
            String imageUrl = imageJObj.getString("url");

//            imgAlbum.setImageUrl(imageUrl, imgLoader);

            String answerDate = albumAnswer.getString("release_date");
//            answer = answerDate;
            String album1Date = album1.getString("release_date");
            String album2Date = album2.getString("release_date");

//            Vector<String> answersStr = new Vector<>();
            data.add(imageUrl);
            data.add(answerDate);
            data.add(album1Date);
            data.add(album2Date);

//            Collections.shuffle(answersStr);
//
//            btnAlbum1.setText(answersStr.get(0));
//            btnAlbum2.setText(answersStr.get(1));
//            btnAlbum3.setText(answersStr.get(2));

            ((Question2Activity)context).afficherQuestion2(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getResponse(JSONObject response) {
        collectedResponse.add(response);
        System.out.println(response.toString());
        if (collectedResponse.size() == vecUrlAlbums.size()) {
            Question2Activity question2Activity = (Question2Activity) context;
            question2Activity.activateBtns();
            generateQuestion2();
        }
    }

}


