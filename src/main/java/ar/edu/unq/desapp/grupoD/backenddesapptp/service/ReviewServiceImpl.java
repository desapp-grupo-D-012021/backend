package ar.edu.unq.desapp.grupoD.backenddesapptp.service;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Movie;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Review;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public void addReview(ReviewType reviewType){
        dao.save(reviewType);
    }

    @Transactional
    public void rateAReviewPositevely(Integer id){

        Optional<ReviewType> review = dao.findById(id);
        review.ifPresent(reviewType -> reviewType.ratePositevely());

        dao.save(review);
    }

    public void rateAReviewNegatively(Integer id) {
        Optional<ReviewType> review = dao.findById(id);
        review.ifPresent(reviewType -> reviewType.rateNegatively());
        dao.save(review);
    }
}