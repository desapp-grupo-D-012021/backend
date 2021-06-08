package model;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;


public class ReviewsManagerTest {

    private ReviewsManager manager;
    private Media foundMedia;

    private Movie starWarsEpisodeIV;
    private String movieImdbId = "tt0076759";
    private String title = "Star Wars: Episode IV - A New Hope";
    private String originalTitle = "Star Wars: Episode IV - A New Hope";
    private String primaryTitle = "Star Wars: Episode IV - A New Hope";
    private int runtimeMinutes = 121;
    private int year = 1977;

    //Episode IV reviews & critics
    private Review theForceWillBeWithYou;
    private Review legendaryPieceOfCinema;
    private PremiumReview rogerEbert;

    //Episode V reviews & critics

    @Test
    void testEmptyMedia() {
        manager = new ReviewsManager();
        assertTrue(manager.getMedia().isEmpty());
    }

    @Test
    void testAddMedia() {
        manager = new ReviewsManager();
        manager.addMedia(starWarsEpisodeIV);

        assertEquals(1, manager.getMedia().size());
        assertEquals(starWarsEpisodeIV, manager.getMedia().get(0));
    }

    //addReviews tests covered on EpisodeTest
    //TODO: testGetComments()

    @Test
    void testGetReviewsFromMediaByImdbId() {
        manager = new ReviewsManager();
        manager.addMedia(starWarsEpisodeIV);

        theForceWillBeWithYou = mock(Review.class);
        legendaryPieceOfCinema = mock(Review.class);

        starWarsEpisodeIV = new Movie(movieImdbId, title, primaryTitle, originalTitle, year, runtimeMinutes);
        starWarsEpisodeIV.addReview(theForceWillBeWithYou);
        starWarsEpisodeIV.addReview(legendaryPieceOfCinema);

        //FIXME: Media >> Movie
        /*
        starWarsEpisodeIV = manager.getMediaByImdbId(movieImdbId);

        assertEquals(2, starWarsEpisodeIV.getReviews().size());
        assertEquals(theForceWillBeWithYou, starWarsEpisodeIV.getReviews().get(0));
        assertEquals(legendaryPieceOfCinema, starWarsEpisodeIV.getReviews().get(1));
        assertTrue(starWarsEpisodeIV.getPremiumReviews().isEmpty());
         */


    }

}