package ar.edu.unq.desapp.grupoD.backenddesapptp.webService;

import ar.edu.unq.desapp.grupoD.backenddesapptp.exceptions.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.*;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.MediaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


@RestController
@EnableAutoConfiguration
@Api(tags = "Media")
public class MediaController {

    @Autowired
    private MediaService service;

    /*
    @GetMapping("/oldMedia")
    public List<Media> allMedia() {
        return service.findAll();
    }
    */

    @RequestMapping(value = "/media/{imdbId}")
    public ResponseEntity<? extends Serializable> getMediabyId(@PathVariable String id) {
        try {
            Media media = service.getOldMedia(id);
            return ResponseEntity.ok().body(media);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Review not found with id " + id);
        }
    }

    private ResponseEntity<String> mediaNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MEDIA NOT FOUND");
    }

    /*
    @RequestMapping(value = "/media/discover/{query}")
    public ResponseEntity<List<Media>> getMediabyQuery(@RequestParam(required = false) Double rating,
                                                       @RequestParam(required = false) Integer minReviews,
                                                       @RequestParam(required = false) String genre,
                                                       @RequestParam(required = false) Integer fromYear,
                                                       @RequestParam(required = false) Integer toYear,
                                                       @RequestParam(required = false) String actorName) {
        try {
            List<Media> media = service.filterMedia(rating, minReviews, genre, fromYear, toYear, actorName);
            return ResponseEntity.ok().body(media);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Review not found with query ");
        }
    }
    */

    @GetMapping("/media")
    public ResponseEntity<Page<Media>> getMedia(MediaPage mediaPage, MediaSearchCriteria mediaSearchCriteria) {
        return new ResponseEntity<>(service.getMedia(mediaPage,mediaSearchCriteria), HttpStatus.OK);
    }

    @PostMapping("/media")
    public ResponseEntity<Movie> addMedia(@RequestBody Movie media){
        return new ResponseEntity<Movie>(service.addMedia(media), HttpStatus.OK);
    }

}
