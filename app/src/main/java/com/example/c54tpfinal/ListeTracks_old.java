package com.example.c54tpfinal;

import java.util.ArrayList;
import java.util.List;

public class ListeTracks_old {
    List<Track_old> items;
    
    public ListeTracks_old() {
        items = new ArrayList<Track_old>();
    }
    public Track_old get(int i){
        return items.get(i);
    }
}
