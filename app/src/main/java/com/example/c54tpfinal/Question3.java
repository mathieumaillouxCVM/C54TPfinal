package com.example.c54tpfinal;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Collections;
import java.util.Vector;

// Similaire à la classe Question2 (je m'abstiendrai de recommenter les méthodes identiques)
public class Question3 {

    private Context context;
    private Vector<JSONObject> collectedResponse;
    private final Vector<String> vecUrlAlbums;
    private Vector<Integer> indexAlbums;
    private Gson gson;

    public Question3(Context context) {
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
        indexAlbums = new Vector<>();
        remplirIndex();
    }

    public void remplirIndex() {
        for (int i = 0; i < vecUrlAlbums.size(); i++){
            indexAlbums.add(i);
        }
        Collections.shuffle(indexAlbums);
    }

    public void generateQuestion3(){
        Vector<Object> data = new Vector<>();
        if (indexAlbums.size() == 0) {
            remplirIndex();
        }
        GSONAlbum album, albumIntrus;
        String imageUrl, answerIntrus, titre2, titre3;
        gson = new GsonBuilder().create();
        album = gson.fromJson(String.valueOf(collectedResponse.get(indexAlbums.remove(0))), GSONAlbum.class);
        titre2 = album.getTracks().get(0).name;
        titre3 = album.getTracks().get(1).name;
        imageUrl = album.getImages(0).getUrl();
        albumIntrus = gson.fromJson(String.valueOf(collectedResponse.get(indexAlbums.remove(0))), GSONAlbum.class);
        answerIntrus = albumIntrus.getTracks().get(0).name;

        data.add(imageUrl);
        data.add(answerIntrus);
        data.add(titre2);
        data.add(titre3);

        ((Question3Activity)context).afficherQuestion3(data);
    }

    public void getResponse(JSONObject response) {
        collectedResponse.add(response);
        System.out.println(response.toString());
        if (collectedResponse.size() == vecUrlAlbums.size()) {
            Question3Activity question3Activity = (Question3Activity) context;
            question3Activity.activateBtns();
            generateQuestion3();
        }
    }
}
