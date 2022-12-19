package com.example.c54tpfinal;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Vector;

public class Question2 {

    private Context context;
    private final Vector<String> vecUrlAlbums;
    private Vector<JSONObject> collectedResponse;

    public Question2(Context context) {
        this.context = context;
        vecUrlAlbums = new Vector<>();
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/2guirTSEqLizK7j9i1MTTZ");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/7wOOA7l306K8HfBKfPoafr");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/1To7kv722A8SpZF789MZy7");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/1KVGLuPtrMrLlyy4Je6df7");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/4K8bxkPDa5HENw0TK7WxJh");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/1IVa98im1RfxYp6qeOIg2B");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/5o2p8FZAyEOSH7arjJLCJp");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/1XFNz6KIvLyIsLFOiLRKqP");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/7o14zVcXSRk7clV6QCEdOD");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/0OHDiDMyxzWJfwtoeHNCf4");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/6dVIqQ8qmQ5GBnJ9shOYGE");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/3gBVdu4a1MMJVMy6vwPEb8");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/35UJLpClj5EDrhpNIi4DFg");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/6GjwtEZcfenmOf6l18N7T7");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/55RhFRyQFihIyGf61MgcfV");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/0bQglEvsHphrS19FGODEGo");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/14gI3ml0wxlgVrX1ve8zyJ");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/7glwer1Pzyns4h32fHbl53");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/58NXIEYqmq5dQHg9nV9duM");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/5LbHbwejgZXRZAgzVAjkhj");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/4FCoFSNIFhK36holxHWCnc");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/5B4PYA7wNN4WdEXdIJu58a");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/3BSOiAas8BpJOii3kCPyjV");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/5pd9B3KQWKshHw4lnsSLNy");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/57lcTrUlYgfMIPvBUsVU6h");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/5kxuokOacguIqDJRh1ZXRC");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/2JJEIN6LvQJQTJDfnYdDAe");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/335Wo8YySOsJdc0QV9ANvK");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/2Rwf2nPYZQ9aIe4QXACTC7");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/0z7Dc7FRsDH7E4kj32mKyM");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/5jebyF3m9OSl7mPNvsTtTY");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/0VgXvWzdF93KHuNdzzSgaB");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/2qsp2AvGPlKYqPeb22pnkX");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/0yxM1OyaFOZiJhi9eNThE4");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/5mJss6O9hjbeESfqoBX1xM");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/6O2rF8WIEEUPxxOYqWOacF");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/57F44c0MTziVzHPEuJtH9A");
        vecUrlAlbums.add("https://api.spotify.com/v1/albums/0xx56m5AMEIMZlSjdJwxf5");
        collectedResponse = new Vector<JSONObject>();
        for (String url : vecUrlAlbums) {
            GenericRequest request = GenericRequest.getInstance(context);
            request.createRequest(url, this);
        }
    }

    public void generateQuestion2() {
        String imageUrl = null;
        Vector<Object> data = new Vector<>();
        Vector<Integer> indexAlbums = new Vector();
        for (int i = 0; i < vecUrlAlbums.size(); i++){
            indexAlbums.add(i);
        }
        Collections.shuffle(indexAlbums);

        Gson gson = new GsonBuilder().create();
        Album albumAnswer = gson.fromJson(String.valueOf(collectedResponse.get(indexAlbums.get(0))), Album.class);
        Image image = albumAnswer.getImages(0);
        imageUrl = image.getUrl();
        Album album2 = gson.fromJson(String.valueOf(collectedResponse.get(indexAlbums.get(1))), Album.class);
        Album album3 = gson.fromJson(String.valueOf(collectedResponse.get(indexAlbums.get(2))), Album.class);
        String albumAnswerDate = albumAnswer.getRelease_date();
        String album2Date = album2.getRelease_date();
        String album3Date = album3.getRelease_date();


//            ListeTracks tracks = album.getTracks();
//            Track track = tracks.get(0);
//            Artist artist = album.getArtist();
        System.out.println(album2.getRelease_date());
//            System.out.println(track.getName());
//            System.out.println(artist.getName());
//


//            JSONArray albumArray = collectedResponse.get(randomArtist).getJSONArray("items");
//            Vector<Integer> albumIndex = new Vector<>();
//            for (int i = 0; i < albumArray.length(); i++) {
//                albumIndex.add(i);
//            }
//
//            Collections.shuffle(albumIndex);
//            JSONObject albumAnswer = albumArray.getJSONObject(albumIndex.get(0));
//            JSONObject album1 = albumArray.getJSONObject(albumIndex.get(1));
//            JSONObject album2 = albumArray.getJSONObject(albumIndex.get(2));
//            JSONArray imageArr = albumAnswer.getJSONArray("images");
//            JSONObject imageJObj = imageArr.getJSONObject(0);
//            String imageUrl = imageJObj.getString("url");

//            imgAlbum.setImageUrl(imageUrl, imgLoader);

//            String answerDate = albumAnswer.getString("release_date");
//            answer = answerDate;
//            String album1Date = album1.getString("release_date");
//            String album2Date = album2.getString("release_date");

//            Vector<String> answersStr = new Vector<>();
        data.add(imageUrl);
        data.add(albumAnswerDate);
        data.add(album2Date);
        data.add(album3Date);

//            Collections.shuffle(answersStr);
//
//            btnAlbum1.setText(answersStr.get(0));
//            btnAlbum2.setText(answersStr.get(1));
//            btnAlbum3.setText(answersStr.get(2));

        ((Question2Activity)context).afficherQuestion2(data);
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


