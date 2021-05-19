package model;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.PremiumReview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class ReviewPremiumTest {

    private PremiumReview premiumReview;

    @BeforeEach
    void setUp() {
        this.premiumReview = (PremiumReview) new PremiumReview.Builder().withText("Texto Corto")
                        .withExtendedText("Texto Extendido")
                        .withRating(9)
                        .withPublicationDate(LocalDate.of(2021,1,25))
                        .withPlatform("Netflix")
                        .withUserNameInPlatform("Critix")
                        .withLanguage("English")
                        .withLikes(3)
                        .withDislikes(25)
                        .build();

    }

    @Test
    void getRatingOfPremiumReview() {
        Assertions.assertEquals(this.premiumReview.getRating(),9);
    }

    @Test
    void testRateAReviewNegatively(){
        this.premiumReview.rateNegatively();
        Assertions.assertEquals(this.premiumReview.getDislikes(), 26);
        Assertions.assertEquals(this.premiumReview.getLikes(), 3);
    }
}
