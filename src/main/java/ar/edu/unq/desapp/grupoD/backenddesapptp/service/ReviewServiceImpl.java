package ar.edu.unq.desapp.grupoD.backenddesapptp.service;

import ar.edu.unq.desapp.grupoD.backenddesapptp.exceptions.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.*;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.MediaDao;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.ReviewCriteriaRepository;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.ReviewDao;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.SuscriptionDao;
import ar.edu.unq.desapp.grupoD.backenddesapptp.rabbitmq.MessagingConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.management.Notification;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl{

    @Autowired
    private ReviewDao dao;
    @Autowired
    private MediaDao mediaDao;

    @Autowired
    private ReviewCriteriaRepository reviewCriteriaRepository;

    @Autowired
    private SuscriptionDao suscriptionDao;

    @Autowired
    private RabbitTemplate template;

    @Transactional
    public List<ReviewType> findAll() {
        return this.dao.findAll();
    }

    @Transactional
    public ReviewType addReview(ReviewType reviewType, String imdbId){
        Media media = mediaDao.findById(imdbId).get();
        reviewType.setMedia(media);
        notifySubscribers(imdbId);
        return  dao.save(reviewType);
    }

    @Transactional
    private void notifySubscribers(String imdbId) {
        if(suscriptionDao.getAllByImdbId(imdbId) != null){
            NotificationMessage message = new NotificationMessage();
            message.setImdbId(imdbId);
            message.setMessage("Se agrego una review con id " + imdbId);
            template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, message);

        }
    }

    @Transactional
    public List<Review> getReviewsFromMediaByImdbId(String imdbId){
        return mediaDao.findById(imdbId).get().getReviews();
    }

    @Transactional
    public ReviewType getReview(Integer id){
        return dao.findById(id).get();
    }

    @Transactional
    public ReviewType rateAReviewPositevely(Integer id){
        dao.findById(id).ifPresent(reviewType -> reviewType.ratePositevely());
        return dao.findById(id).get();
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
    public Page<ReviewType> getReviewsWithFilters(String imdbId, ReviewPage reviewPage, ReviewSearchCriteria reviewSearchCriteria){
        return reviewCriteriaRepository.findAllWithFilters(imdbId,reviewPage,reviewSearchCriteria);
    }

    @Transactional
    public ReviewType addReport(Integer id, ReviewType.ReportType type) {
        ReviewType review = dao.findById(id).get();
        review.addReport(type);
        return review;
    }


}