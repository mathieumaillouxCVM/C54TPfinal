package com.example.c54tpfinal;

// import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Album {
    ListeTracks_old tracks;
    List<Artist_old> artists;
    List<Image_old> images;
    String release_date;
    int popularity;
    int total_tracks;


    public Album() {
        artists = new ArrayList<Artist_old>();
    }

    public ListeTracks_old getTracks() { return tracks; }

    public Artist_old getArtist(){ return artists.get(0); }

    public Image_old getImages(int i) { return images.get(i); }

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
