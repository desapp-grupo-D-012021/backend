package persistence;

import model.Movie;
import model.PremiumReview;
import model.Review;
import model.ReviewType;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Configuration
@Repository
public interface ReviewDao extends CrudRepository<Review,Integer> {
    void addReview(Review review, Movie movie);
    void rateAReviewPositevely(ReviewType review);

    void rateAReviewNegatively(ReviewType premiumReview);
    List<Review> findAll();
}
