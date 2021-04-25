package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ReviewPremiumTest {

    private PremiumReview builder;
    @BeforeEach
    void setUp() {
        this.builder = (PremiumReview) new PremiumReview.Builder().withText("Texto Corto")
                        .withExtendedText("Texto Extendido")
                        .withRating(9)
                        .withPublicationDate(LocalDate.of(2021,1,25))
                        .withPlatform("Netflix")
                        .withUserNameInPlatform("Critix")
                        .withLanguage("English")
                        .build();
    }

    @Test
    void getRatingOfPremiumReview() {
        Assertions.assertEquals(this.builder.getRating(),9);
    }
}
