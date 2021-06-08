package ar.edu.unq.desapp.grupoD.backenddesapptp.service;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Media;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.MediaPage;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.MediaSearchCriteria;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Movie;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.MediaCriteriaRepository;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.MediaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MediaService {

    @Autowired
    private MediaDao dao;
    private MediaCriteriaRepository mediaCriteriaRepository;

    @Transactional
    public List<Media> findAll() {
        return this.dao.findAll();
    }

    @Transactional
    public Media getOldMedia(String id){
        return dao.findById(id).get();
    }

    public Page<Media> getMedia(MediaPage mediaPage, MediaSearchCriteria mediaSearchCriteria) {
        return mediaCriteriaRepository.findAllWithFilters(mediaPage, mediaSearchCriteria);
    }

    public Movie addMedia(Movie media){
        return dao.save(media);
    }
}