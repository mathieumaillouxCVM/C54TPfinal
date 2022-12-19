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
    List<Image> images;
    String release_date;
    int popularity;
    int total_tracks;


    public Album() {
        artists = new ArrayList<Artist>();
    }

    public ListeTracks getTracks() { return tracks; }

    public Artist getArtist(){ return artists.get(0); }

    public Image getImages(int i) { return images.get(i); }

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
