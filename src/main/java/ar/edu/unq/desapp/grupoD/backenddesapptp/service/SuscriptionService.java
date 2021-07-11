package ar.edu.unq.desapp.grupoD.backenddesapptp.service;

import ar.edu.unq.desapp.grupoD.backenddesapptp.exceptions.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Suscription;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.MediaDao;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.SuscriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SuscriptionService {

    @Autowired
    private SuscriptionDao dao;

    @Autowired
    private MediaDao mediaDao;

    @Transactional
    public Suscription addSuscription(Suscription suscription) {
        if (mediaDao.existsById(suscription.getImdbId())) {
            return dao.save(suscription);
        } else {
            throw new ResourceNotFoundException("Media not found with id " + suscription.getImdbId());
        }
    }

    @Transactional
    public List<Suscription> getAllByImdbId(String imdbId){
        return dao.getAllByImdbId(imdbId);
    }
}
