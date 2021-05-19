package model;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.PremiumReview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.ReviewDao;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.ReviewServiceImpl;

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

        this.service = new ReviewServiceImpl();
    }

    @Test
    void getRatingOfPremiumReview() {
        Assertions.assertEquals(this.premiumReview.getRating(),9);
    }

    @Test
    void testRateAReviewNegatively(){
        this.service.rateAReviewNegatively(1);
        Assertions.assertEquals(this.premiumReview.getDislikes(), 1);
        Assertions.assertEquals(this.premiumReview.getLikes(), 0);
    }
}
