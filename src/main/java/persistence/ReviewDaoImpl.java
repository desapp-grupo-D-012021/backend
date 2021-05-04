package persistence;

import model.Movie;
import model.Review;
import model.ReviewType;

public class ReviewDaoImpl implements ReviewDao {

    @Override
    public void addReview(Review review, Movie movie) {
        movie.addReview(review);
    }

    @Override
    public void rateAReviewPositevely(ReviewType review) {
        review.ratePositevely();
    }
}
