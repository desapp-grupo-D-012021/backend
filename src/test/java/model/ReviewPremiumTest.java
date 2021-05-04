package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.ReviewDao;
import persistence.ReviewDaoImpl;
import service.ReviewServiceImpl;

import java.time.LocalDate;

public class ReviewPremiumTest {

    private PremiumReview premiumReview;
    private ReviewServiceImpl service;
    private ReviewDao dao;

    @BeforeEach
    void setUp() {
        this.premiumReview = (PremiumReview) new PremiumReview.Builder().withText("Texto Corto")
                        .withExtendedText("Texto Extendido")
                        .withRating(9)
                        .withPublicationDate(LocalDate.of(2021,1,25))
                        .withPlatform("Netflix")
                        .withUserNameInPlatform("Critix")
                        .withLanguage("English")
                        .build();

        this.dao = new ReviewDaoImpl();
        this.service = new ReviewServiceImpl(dao);
    }

    @Test
    void getRatingOfPremiumReview() {
        Assertions.assertEquals(this.premiumReview.getRating(),9);
    }

    @Test
    void testRateAReviewNegatively(){
        this.service.rateAReviewNegatively(this.premiumReview);
        Assertions.assertEquals(this.premiumReview.getDislikes(), 1);
        Assertions.assertEquals(this.premiumReview.getLikes(), 0);
    }
}
