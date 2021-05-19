package model;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
public class ReviewTest {

    private Review review;

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
                        .withLikes(11)
                        .withDislikes(3)
                        .build();

    }

    @Test
    void testGetCityOfReviewer() {

        Assertions.assertEquals(this.review.getCity(),"Quilmes");
    }

    @Test
    void testRateAReviewPositevely(){
        this.review.ratePositevely();
        Assertions.assertEquals(this.review.getLikes(), 12);
    }

}