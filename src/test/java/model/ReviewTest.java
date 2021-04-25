package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ReviewTest {

    private Review builder;

    @BeforeEach
    void setUp(){
        this.builder = (Review) new Review.Builder2().withCity("Quilmes")
                        .withSpoilerAlert(false)
                        .withExtendedText("Texto Extendido")
                        .withText("Texto Corto")
                        .withRating(5)
                        .withPublicationDate(LocalDate.of(2020,2,13))
                        .withPlatform("Netflix")
                        .withUserNameInPlatform("Yisus")
                        .withLanguage("Español")
                        .build();
    }

    @Test
    void testGetCityOfReviewer() {

        Assertions.assertEquals(this.builder.getCity(),"Quilmes");
    }
}