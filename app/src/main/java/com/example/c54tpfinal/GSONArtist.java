package com.example.c54tpfinal;

import java.util.ArrayList;
import java.util.List;

// Classe GSONArtist pour décortiquer les réponses de l'API concernant des artistes

public class GSONArtist {
    String name;
    int popularity;
    List<Image> images;

    public GSONArtist() {
        images = new ArrayList<Image>();
    }

    public Image getImages(int i) { return images.get(i); }

    public class Image {
        String url;
        public String getUrl() {
            return url;
        }
    }

}
