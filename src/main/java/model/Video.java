package model;

public abstract class Video extends Media {

    private int runtimeMinutes;

    public Video(String imdbId, String title, String primaryTitle, String originalTitle, int runtimeMinutes){
        super(imdbId, title, primaryTitle, originalTitle);
        this.runtimeMinutes = runtimeMinutes;
    }

}
