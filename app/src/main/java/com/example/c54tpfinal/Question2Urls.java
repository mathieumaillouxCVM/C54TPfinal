package com.example.c54tpfinal;

import java.util.Vector;

public class Question2Urls {

    private final Vector<String> vecUrlAlbums;

    public Question2Urls() {
        this.vecUrlAlbums = new Vector<>();
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/3inCNiUr4R6XQ3W43s9Aqi/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/6olE6TJLqED3rqDCT0FyPh/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/40Yq4vzPs9VNUrIBG5Jr2i/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/64tNsm6TnZe2zpcMVMOoHL/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/1w5Kfo2jwwIPruYS2UWh56/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/2UazAtjfzqBF0Nho2awK4z/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/5xUf6j4upBrXZPg6AI4MRK/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/5SHQUMAmEK5KmuSb0aDvsn/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/2zMQOJ4Cyl4BYbw6WqaO3h/albums");
        vecUrlAlbums.add("https://api.spotify.com/v1/artists/267VY6GX5LyU5c9M85ECZQ/albums");
    }
    public Vector<String> getVecUrlAlbums() {
        return this.vecUrlAlbums;
    }
}
