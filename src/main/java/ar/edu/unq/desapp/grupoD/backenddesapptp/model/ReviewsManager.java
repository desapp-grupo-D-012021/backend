package ar.edu.unq.desapp.grupoD.backenddesapptp.model;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Media;

import java.util.ArrayList;
import java.util.List;

public class ReviewsManager {

    private List<Media> media;

    public ReviewsManager(){
        media = new ArrayList<Media>();
    }

    public List<Media> getMedia(){
        return this.media;
    }

    public Media getMediaByImdbId(String mediaImdbId){
        return this.media.get(0); //FIXME: stream & find
    }

    public void addMedia(Media media){
        this.media.add(media);
    }
}
