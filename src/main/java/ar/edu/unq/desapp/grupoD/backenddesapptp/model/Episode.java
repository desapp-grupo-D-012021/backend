package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Episode")
public class Episode extends Video {
    
    public Episode(String imdbId, String title, String primaryTitle, String originalTitle, String genre, int year, int runtimeMinutes){
        super(imdbId, title, primaryTitle, originalTitle, genre, year, runtimeMinutes);
    }
}
