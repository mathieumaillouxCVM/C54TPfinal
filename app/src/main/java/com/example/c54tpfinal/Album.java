package com.example.c54tpfinal;

// import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Album {
    ListeTracks tracks;
    List<Artist> artists;
    String release_date;
    int popularity;
    int total_tracks;


    public Album() {
        artists = new ArrayList<Artist>();
    }

    public Artist getArtist(){
        return artists.get(0);
    }

    public ListeTracks getTracks() {
        return tracks;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getTotal_tracks() {
        return total_tracks;
    }
}
