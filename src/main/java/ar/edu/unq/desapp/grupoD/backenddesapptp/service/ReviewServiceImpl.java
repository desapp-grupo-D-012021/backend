package ar.edu.unq.desapp.grupoD.backenddesapptp.service;

import ar.edu.unq.desapp.grupoD.backenddesapptp.exceptions.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Review;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewPage;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewSearchCriteria;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.ReviewCriteriaRepository;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl{

    @Autowired
    private ReviewDao dao;

    @Autowired
    private ReviewCriteriaRepository reviewCriteriaRepository;

    @Transactional
    public List<ReviewType> findAll() {
        return this.dao.findAll();
    }

    @Transactional
    public ReviewType addReview(ReviewType reviewType){
        return  dao.save(reviewType);
    }

    @Transactional
    public ReviewType getReview(Integer id){
        return dao.findById(id).get();
    }

    @Transactional
    public ReviewType rateAReviewPositevely(Integer id){

        ReviewType review = dao.findById(id).get();
        review.ratePositevely();

        return review;
    }

    @Transactional
    public ReviewType rateAReviewNegatively(Integer id) {
        ReviewType review = dao.findById(id).get();
        review.rateNegatively();

        return review;
    }

    @Transactional
    public ReviewType findReviewByIdAndPlatform(Integer id, String platform) throws Exception {
        ReviewType reviewType = dao.findReviewByIdAndPlatform(id, platform);
        if(reviewType == null){
            throw new Exception();
        }
        return reviewType;
    }

    @Transactional
    public Page<ReviewType> getReviews(ReviewPage reviewPage, ReviewSearchCriteria reviewSearchCriteria){
        return reviewCriteriaRepository.findAllWithFilters(reviewPage,reviewSearchCriteria);
    }


    @Transactional
    public ReviewType addReport(Integer id, ReviewType.ReportType type) {
        ReviewType review = dao.findById(id).get();
        review.addReport(type);
        return review;
    }
}