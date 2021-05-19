package ar.edu.unq.desapp.grupoD.backenddesapptp.service;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl{

    @Autowired
    private ReviewDao dao;

    @Transactional
    public List<ReviewType> findAll() {
        return this.dao.findAll();
    }

    @Transactional
    public ReviewType addReview(ReviewType reviewType){
        return  dao.save(reviewType);
    }

    @Transactional
    public Optional<ReviewType> getReview(Integer id){
        return dao.findById(id);
    }

    @Transactional
    public ReviewType rateAReviewPositevely(Integer id){

        Optional<ReviewType> review = dao.findById(id);
        review.ifPresent(ReviewType::ratePositevely);

        return dao.save(review);
    }

    public void rateAReviewNegatively(Integer id) {
        Optional<ReviewType> review = dao.findById(id);
        review.ifPresent(reviewType -> reviewType.rateNegatively());
        dao.save(review);
    }
}