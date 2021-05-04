package model;

import java.util.ArrayList;
import java.util.List;

public class Series extends Media {

    private List<Episode> episodes;

    public Series(String imdbId, String title, String primaryTitle, String originalTitle) {
        super(imdbId, title, primaryTitle, originalTitle);
        this.episodes = new ArrayList<Episode>();
    }

    public List<Episode> getEpisodes() {
        return this.episodes;
    }

    public void addEpisode(Episode episode) {
        this.episodes.add(episode);
    }

}
