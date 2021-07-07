package ar.edu.unq.desapp.grupoD.backenddesapptp.persistence;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Review;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface ReviewDao extends CrudRepository<ReviewType,Integer> {

    List<ReviewType> findAll();

    @Override
    <S extends ReviewType> S save(S entity);

    @Override
    Optional<ReviewType> findById(Integer integer);

    @Query(value = "SELECT r FROM ReviewType r WHERE r.id = ?1 and r.platform = ?2")
    ReviewType findReviewByIdAndPlatform(Integer id, String platform);


}
