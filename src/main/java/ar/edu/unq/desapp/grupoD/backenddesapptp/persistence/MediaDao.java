package ar.edu.unq.desapp.grupoD.backenddesapptp.persistence;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Media;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface MediaDao extends CrudRepository<Media,String> {

    List<Media> findAll();

    @Override
    Optional<Media> findById(String str);

}
