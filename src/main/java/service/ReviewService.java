package service;

import model.Movie;
import model.PremiumReview;
import model.Review;
import model.ReviewType;

import java.util.List;

public interface ReviewService {

    void addReview(Review builder, Movie mockMovie);
    void rateAReviewPositevely(ReviewType review);

    void rateAReviewNegatively(ReviewType premiumReview);

    List<Review> findAll();
}
