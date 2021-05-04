package persistence;

import model.Movie;
import model.PremiumReview;
import model.Review;
import model.ReviewType;

public interface ReviewDao {
    void addReview(Review review, Movie movie);
    void rateAReviewPositevely(ReviewType review);

    void rateAReviewNegatively(ReviewType premiumReview);
}
