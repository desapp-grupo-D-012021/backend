package ar.edu.unq.desapp.grupoD.backenddesapptp.service;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.*;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.MediaCriteriaRepository;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.MediaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MediaService {

    @Autowired
    private MediaDao dao;
    @Autowired
    private MediaCriteriaRepository mediaCriteriaRepository;

    @Transactional
    @Cacheable(value = "mediaCache")
    public List<Media> findAll() {
        return this.dao.findAll();
    }

    @Transactional
    public Media getMediaById(String id){
        return dao.findById(id).get();
    }

    public Page<Media> getMedia(MediaPage mediaPage, MediaSearchCriteria mediaSearchCriteria) {
        return mediaCriteriaRepository.findAllWithFilters(mediaPage, mediaSearchCriteria);
    }

    public Media addMedia(Media media){
        return dao.save(media);
    }

    public Movie addMovie(Movie movie){
        return dao.save(movie);
    }

    public Series addSeries(Series series){
        return dao.save(series);
    }

    public Episode addEpisode(Episode episode){
        return dao.save(episode);
    }
}