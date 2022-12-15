package com.example.c54tpfinal;

import java.util.ArrayList;
import java.util.List;

public class ListeTracks {
    List<Track> items;
    
    public ListeTracks() {
        items = new ArrayList<Track>();
    }
    public Track get(int i){
        return items.get(i);
    }
}
