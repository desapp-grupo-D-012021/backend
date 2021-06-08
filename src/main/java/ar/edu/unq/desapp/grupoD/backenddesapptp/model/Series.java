package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Series")
public class Series extends Media {

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "series_id")
    private List<Episode> episodes;

    public Series(String imdbId, String title, String primaryTitle, String originalTitle, String genre, int year) {
        super(imdbId, title, primaryTitle, originalTitle, genre, year);
        this.episodes = new ArrayList<Episode>();
    }

    public List<Episode> getEpisodes() {
        return this.episodes;
    }

    public void addEpisode(Episode episode) {
        this.episodes.add(episode);
    }

}
