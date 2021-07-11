package ar.edu.unq.desapp.grupoD.backenddesapptp.persistence;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Suscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SuscriptionDao extends CrudRepository<Suscription, Integer> {

    List<Suscription> getAllByImdbId(String imdbId);
}
