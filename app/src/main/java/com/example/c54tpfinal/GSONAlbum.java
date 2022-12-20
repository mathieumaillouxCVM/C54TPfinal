package com.example.c54tpfinal;

import java.util.ArrayList;
import java.util.List;

public class GSONAlbum {
    ListeTracks tracks;
    List<Artist> artists;
    List<Image> images;
    String release_date;
    int popularity;
    int total_tracks;

    public GSONAlbum() {
        artists = new ArrayList<Artist>();
        images = new ArrayList<Image>();
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


    public class ListeTracks {
        List<Track> items;
        public ListeTracks() {
            items = new ArrayList<Track>();
        }
        public Track get(int i){
            return items.get(i);
        }

        public class Track {
            String name;
            int track_number;

            public String getName() {
                return name;
            }

            public int getTrack_number() {
                return track_number;
            }
        }
    }

    public class Artist {
        String name;

        public String getName() {
            return name;
        }

    }

    public class Image {
        String url;
        public String getUrl() {
            return url;
        }

    }
}
