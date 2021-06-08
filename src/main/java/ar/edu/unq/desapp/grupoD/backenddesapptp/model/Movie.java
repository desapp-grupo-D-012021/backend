package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Movie")
public class Movie extends Video {

    public Movie(){}

    public Movie(String imdbId, String title, String primaryTitle, String originalTitle, int year, int runtimeMinutes){
        super(imdbId, title, primaryTitle, originalTitle, year, runtimeMinutes);
    }
}
