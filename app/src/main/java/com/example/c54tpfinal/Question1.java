package com.example.c54tpfinal;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Vector;

// Classe Question1 (similaire à Question 2 et 3) qui contient les url qui seront
// envoyés au singleton GenericRequest.  Cette classe est responsable de générer aléatoirement
// des question comparatives avec l'ensemble des données récupérées. Elle envoie des données
// qui seront affichées et vérifiées par la vue (comparées).  C'est un point faible de mon design
// qui aurait pu être amélioré si ces infos (bonne réponse) avaient été accessibles ici via des getter
// pour minimiser la logique dans l'écouteur de l'activité (vue), n'empêche que c'est très léger
// et discutable j'imagine.

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
        // Je fais toute les requêtes relatives aux url plus haut. Les réponses sont
        // récupérées par la méthode getResponse et mises dans un vecteur de JSONObject (collectedResponse)
        for (String url : vecUrlArtists) {
            GenericRequest request = GenericRequest.getInstance(context);
            request.createRequest(url, this);
        }
        // Vecteur de Integer contenant les index possibles sur le vecteur collectedResponse
        indexArtists = new Vector<>();
        remplirIndex();
    }

    public void remplirIndex() {
        // Remplir le vecteur d'index et utiliser la méthode shuffle pour que
        // ne soit pas toujours posé la même question
        for (int i = 0; i < vecUrlArtists.size(); i++){
            indexArtists.add(i);
        }
        Collections.shuffle(indexArtists);
    }

    public void generateQuestion1() {
        // Vecteur pour récupérer les données relatives à la question générée que sera
        // retourné via une méthode afficherQuestion dans QuestionActivity (vue)
        Vector<Object> data = new Vector<>();
        if (indexArtists.size() == 0) {
            remplirIndex();
        }
        int index1 = indexArtists.remove(0);
        int index2 = indexArtists.remove(0);
        gson = new GsonBuilder().create();
        // Utilisation de mes classes GSON tout dépendant du type de question afin de récupérer
        // les informations nécessaires pour la question (image, nom, date, titre, popularité ...)
        GSONArtist artist1 = gson.fromJson(String.valueOf(collectedResponse.get(index1)), GSONArtist.class);
        GSONArtist artist2 = gson.fromJson(String.valueOf(collectedResponse.get(index2)), GSONArtist.class);
        String imageUrl1 = artist1.getImages(0).getUrl();
        String imageUrl2 = artist2.getImages(0).getUrl();
        String name1 = artist1.name;
        String name2 = artist2.name;
        int popularity1 = artist1.popularity;
        int popularity2 = artist2.popularity;
        data.add(imageUrl1);
        data.add(imageUrl2);
        data.add(name1);
        data.add(name2);
        data.add(popularity1);
        data.add(popularity2);
        ((Question1Activity)context).afficherQuestion1(data);
    }

    // Méthode appelé par le singleton GenericRequest pour récupérer les résultats des requêtes
    public void getResponse(JSONObject response) {
        collectedResponse.add(response);
        System.out.println(response.toString());
        // Seulement lorsque le vecteur à la même dimension que la quantité d'url (nombres de
        // requêtes) que les questions peuvent être générées. C'est aussi seulement à ce moment
        // que les boutons sont activés dans la vue afin de donner une réponse (éviter un bug).
        if (collectedResponse.size() == vecUrlArtists.size()) {
            Question1Activity question1Activity = (Question1Activity) context;
            question1Activity.activateBtns();
            generateQuestion1();
        }
    }
}
