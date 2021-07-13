import ar.edu.unq.desapp.grupoD.backenddesapptp.cache.RedisConfig;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Episode;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Media;
import ar.edu.unq.desapp.grupoD.backenddesapptp.persistence.MediaDao;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.MediaService;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Import({RedisConfig.class, MediaService.class})
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(classes = {CacheAutoConfiguration.class, RedisAutoConfiguration.class})
@EnableCaching
public class MediaServiceCachingIntegrationTest {

    @MockBean
    private MediaDao mockMediaRepository;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private CacheManager cacheManager;

    private Episode winterIsComing;
    private String episodeImdbId = "tt1480055";
    private String title = "Winter Is Coming";
    private String originalTitle = "Winter Is Coming";
    private String primaryTitle = "Winter Is Coming ";
    private String genre = "Action";
    private int runtimeMinutes = 62;
    private int year = 2007;

    @Test
    public void givenRedisCaching_whenFindItemById_thenItemReturnedFromCache() {
        Media aMedia = new Episode(episodeImdbId, title, primaryTitle, originalTitle, genre, year, runtimeMinutes);
        given(mockMediaRepository.findById(episodeImdbId)).willReturn(Optional.of(aMedia));

        Media mediaCacheMiss = mediaService.getMediaById(episodeImdbId);
        Media mediaCacheHit = mediaService.getMediaById(episodeImdbId);

        assertThat(mediaCacheMiss).isEqualTo(aMedia);
        assertThat(mediaCacheHit).isEqualTo(aMedia);

        verify(mockMediaRepository, times(1)).findById(episodeImdbId);
        assertThat(mediaFromCache()).isEqualTo(aMedia);
    }

    private Object mediaFromCache() {
        return cacheManager.getCache("mediaCache").get(episodeImdbId).get();
    }
}
