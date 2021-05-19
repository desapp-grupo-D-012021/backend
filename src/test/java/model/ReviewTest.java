package model;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Movie;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.ReviewDao;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.ReviewServiceImpl;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class ReviewTest {

    private Review review;
    private Movie mockMovie;
    private ReviewServiceImpl service;
    private ReviewDao dao;

    @BeforeEach
    void setUp(){
        this.review = (Review) new Review.Builder().withCity("Quilmes")
                        .withSpoilerAlert(false)
                        .withExtendedText("Texto Extendido")
                        .withText("Texto Corto")
                        .withRating(5)
                        .withPublicationDate(LocalDate.of(2020,2,13))
                        .withPlatform("Netflix")
                        .withUserNameInPlatform("Yisus")
                        .withLanguage("Español")
                        .build();
        this.service = new ReviewServiceImpl();
    }

    @Test
    void testGetCityOfReviewer() {

        Assertions.assertEquals(this.review.getCity(),"Quilmes");
    }
    @Test

    void testAddReviewToMovie(){
        this.mockMovie = mock(Movie.class);
        this.service.addReview(this.review);
        verify(this.mockMovie).addReview(this.review);
    }

    @Test
    void testRateAReviewPositevely(){
        this.service.rateAReviewPositevely(1);
        Assertions.assertEquals(this.review.getLikes(), 1);
    }

}