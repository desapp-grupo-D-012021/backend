package service;

import model.Movie;
import model.Review;
import model.ReviewType;

public interface ReviewService {

    void addReview(Review builder, Movie mockMovie);
    void rateAReviewPositevely(ReviewType review);
}
