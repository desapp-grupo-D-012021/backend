package ar.edu.unq.desapp.grupoD.backenddesapptp.persistence;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface ReviewDao extends CrudRepository<ReviewType,Integer> {

    List<ReviewType> findAll();

    @Override
    <S extends ReviewType> S save(S entity);

    ReviewType save(Optional<ReviewType> review);

    @Override
    Optional<ReviewType> findById(Integer integer);
}
