package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Movie")
public class Movie extends Video {

    public Movie(String imdbId, String title, String primaryTitle, String originalTitle, int runtimeMinutes){
        super(imdbId, title, primaryTitle, originalTitle, runtimeMinutes);
    }
}
