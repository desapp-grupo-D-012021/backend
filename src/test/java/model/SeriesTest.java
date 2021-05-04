package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;


public class SeriesTest {

    private Series series;
    private String seriesImdbId = "tt0944947";
    private String title = "Game of Thrones";
    private String originalTitle = "Game of Thrones";
    private String primaryTitle = "Game of Thrones";
    //private List<Episode> emptyEpisodesList = new ArrayList<Episode>();
    private Episode winterIsComing;

    @Test
    void testEmptyEpisodesList() {
        series = new Series(seriesImdbId, title, primaryTitle, originalTitle);
        assertTrue(series.getEpisodes().isEmpty());
    }

    @Test
    void testAddEpisode() {
        winterIsComing = mock(Episode.class);

        series = new Series(seriesImdbId, title, primaryTitle, originalTitle);
        series.addEpisode(winterIsComing);

        assertEquals(1, series.getEpisodes().size());
        assertEquals(winterIsComing, series.getEpisodes().get(0));
    }
}