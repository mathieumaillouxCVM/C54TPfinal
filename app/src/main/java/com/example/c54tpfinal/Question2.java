package com.example.c54tpfinal;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import java.util.Collections;
import java.util.Vector;

// Similaire à la classe Question1 (je m'abstiendrai de recommenter les méthodes identiques)
public class Question2 {

    private Context context;
    private Vector<JSONObject> collectedResponse;
    private final Vector<String> vecUrlAlbums;
    private Vector<Integer> indexAlbums;
    private Vector<Integer> years;
    private Gson gson;

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
        // Je rajoute un vecteur contenant une plage d'années disponibles.  C'est pour éviter
        // d'utiliser l'année d'autres albums qui auraient pu être identiques à celle de celui
        // dont je présente la pochette.
        years = new Vector<Integer>();
        for (int i = 1987; i < 2003; i++) {
            years.add(i);
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

    public void generateQuestion2() {
        Vector<Object> data = new Vector<>();
        String imageUrl = null;
        String answer = null;
        String titre = null;
        GSONAlbum albumAnswer;
        // Je fais un do while afin de retirer les albums qui seraient trop récents (malgré l'info
        // sur le site de spotify, j'ai eu de mauvaises surprises avec certains albums "remastérisés" récents.
        do {
            if (indexAlbums.size() == 0) {
                remplirIndex();
            }
            gson = new GsonBuilder().create();
            albumAnswer = gson.fromJson(String.valueOf(collectedResponse.get(indexAlbums.remove(0))), GSONAlbum.class);
            String date = albumAnswer.getRelease_date();
            // Je conserve seulement l'année
            String[] dateSplit = date.split("-");
            answer = dateSplit[0];
            titre = albumAnswer.getName();
        } while (Integer.parseInt(answer) >= 2003); // Filtrer certains albums "remastérisés" récents
        GSONAlbum.Image image = albumAnswer.getImages(0);
        imageUrl = image.getUrl();

        String date2, date3;
        do {
            Collections.shuffle(years);
            date2 = String.valueOf(years.get(0));
            date3 = String.valueOf(years.get(1));
        } while (date2.equals(answer) || date3.equals(answer));

        data.add(titre);
        data.add(imageUrl);
        data.add(answer);
        data.add(date2);
        data.add(date3);

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


