package service;

import model.*;
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

    @Override
    public void rateAReviewNegatively(ReviewType premiumReview) {
        this.dao.rateAReviewNegatively(premiumReview);
    }

}