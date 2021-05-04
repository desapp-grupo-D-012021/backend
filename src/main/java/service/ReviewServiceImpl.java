package service;

import model.Movie;
import model.Review;
import model.ReviewType;
import model.ReviewType;
import persistence.ReviewDao;

public class ReviewServiceImpl implements ReviewService {

    private ReviewDao dao;
    public ReviewServiceImpl(ReviewDao dao) {
        this.dao = dao;

    }
    @Override
    public void addReview(Review review, Movie mockMovie) {
        this.dao.addReview(review, mockMovie);
    }

    @Override
    public void rateAReviewPositevely(ReviewType review) {
        this.dao.rateAReviewPositevely(review);
    }
}