package model;

public class Movie extends Video {

    public Movie(String imdbId, String title, String primaryTitle, String originalTitle, int runtimeMinutes){
        super(imdbId, title, primaryTitle, originalTitle, runtimeMinutes);
    }
}
