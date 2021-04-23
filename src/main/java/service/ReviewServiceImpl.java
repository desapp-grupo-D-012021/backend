package service;

import model.TypeReview;
import persistence.ReviewDao;

public class ReviewServiceImpl implements ReviewService {

    private ReviewDao dao;
    public ReviewServiceImpl(ReviewDao dao) {
        this.dao = dao;

    }
    @Override
    public void addReview (TypeReview review){
        this.dao.addReview(review);
    }
}