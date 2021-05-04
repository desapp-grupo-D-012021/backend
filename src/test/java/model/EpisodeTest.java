package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.mock;


public class EpisodeTest {

    private Episode winterIsComing;
    private String episodeImdbId = "tt1480055";
    private String title = "Winter Is Coming";
    private String originalTitle = "Winter Is Coming";
    private String primaryTitle = "Winter Is Coming ";
    private int runtimeMinutes = 62;

    private Review lateToTheParty;

    @Test
    void testRuntimeMinutes() {
        winterIsComing = new Episode(episodeImdbId, title, primaryTitle, originalTitle, runtimeMinutes);
        assertEquals(62, winterIsComing.getRuntimeMinutes());
    }

    @Test
    void testEmptyReviewsList() {
        winterIsComing = new Episode(episodeImdbId, title, primaryTitle, originalTitle, runtimeMinutes);
        assertTrue(winterIsComing.getReviews().isEmpty());
    }

    @Test
    void testEmptyPremiumReviewsList() {
        winterIsComing = new Episode(episodeImdbId, title, primaryTitle, originalTitle, runtimeMinutes);
        assertTrue(winterIsComing.getPremiumReviews().isEmpty());
    }

    @Test
    void testAddReview() {
        lateToTheParty = mock(Review.class);
    }
}