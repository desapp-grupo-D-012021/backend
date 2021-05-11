package persistence;

import model.Movie;
import model.PremiumReview;
import model.Review;
import model.ReviewType;

import java.util.List;
import java.util.Optional;

public class ReviewDaoImpl implements ReviewDao {

    @Override
    public void addReview(Review review, Movie movie) {
        movie.addReview(review);
    }

    @Override
    public void rateAReviewPositevely(ReviewType review) {
        review.ratePositevely();
    }

    @Override
    public void rateAReviewNegatively(ReviewType premiumReview) {
        premiumReview.rateNegatively();
    }

    @Override
    public List<Review> findAll() {
        return null;
    }

    @Override
    public <S extends Review> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Review> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Review> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }


    @Override
    public Iterable<Review> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Review entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Review> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
