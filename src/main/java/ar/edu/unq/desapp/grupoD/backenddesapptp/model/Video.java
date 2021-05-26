package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Video")
public abstract class Video extends Media {

    @Column
    private int runtimeMinutes;

    public Video(String imdbId, String title, String primaryTitle, String originalTitle, int runtimeMinutes){
        super(imdbId, title, primaryTitle, originalTitle);
        this.runtimeMinutes = runtimeMinutes;
    }

    public int getRuntimeMinutes() {
        return this.runtimeMinutes;
    }

}
