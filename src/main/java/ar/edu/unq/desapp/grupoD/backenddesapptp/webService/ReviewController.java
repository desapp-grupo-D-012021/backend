package ar.edu.unq.desapp.grupoD.backenddesapptp.webService;

import ar.edu.unq.desapp.grupoD.backenddesapptp.exceptions.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.*;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.ReviewServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;


import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.util.List;


@RestController
@EnableAutoConfiguration
@Api(tags = "Reviews")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl service;


    @GetMapping("/api/reviews")
    //@ApiOperation(value = "This method is used to get the reviews.")
    public List<ReviewType> allReviews() {
        return service.findAll();
    }


    private ResponseEntity<String> reviewNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
    }

    @PostMapping("/api/reviews/{imdbId}")
    public ResponseEntity addReview(@RequestBody Review review,@PathVariable String imdbId){
        try{
            service.addReview(review, imdbId);
            return ResponseEntity.ok().body(review);
        }catch (Exception e) {
            throw new ResourceNotFoundException("Media not found with id " + imdbId);
        }
    }

    @PostMapping("/api/premiumReviews/{imdbId}")
    public ResponseEntity addPremiumReview(@RequestBody PremiumReview review, @PathVariable String imdbId){
        try{
            service.addReview(review, imdbId);
            return ResponseEntity.ok().body(review);
        }catch (Exception e) {
            throw new ResourceNotFoundException("Media not found with id " + imdbId);
        }
    }

    @PatchMapping("/api/reviews/like/{id}")
    public ResponseEntity<? extends Serializable> rateAReviewPositively(@PathVariable Integer id){
       try{
           ReviewType review = service.rateAReviewPositevely(id);
           return ResponseEntity.ok().body(review);
       }catch (Exception e){
           throw new ResourceNotFoundException("Review not found with id " + id);
       }

    }

    @PatchMapping("/api/reviews/dislike/{id}")
    public ResponseEntity<? extends Serializable> rateAReviewNegatively(@PathVariable Integer id){
        try{
            ReviewType review = service.rateAReviewNegatively(id);
            return ResponseEntity.ok().body(review);
        }catch (Exception e){
            throw new ResourceNotFoundException("Review not found with id " + id);
        }
    }

    @GetMapping("/api/reviews/{id}/{platform}")
    public ResponseEntity findReviewByIdInPlatform(@PathVariable Integer id, @PathVariable String platform){
        try{
            ReviewType reviewType = service.findReviewByIdAndPlatform(id,platform);
            return ResponseEntity.ok().body(reviewType);
        }catch (Exception e){
            throw new ResourceNotFoundException("No existe reviews con id " + id + " en plataforma: " + platform );
        }
    }

    @PatchMapping("/api/reviews/report/{id}/{type}")
    public ResponseEntity reportAReview(@PathVariable Integer id, @PathVariable ReviewType.ReportType type ){
        try{
            ReviewType review = service.getReview(id);
            service.addReport(id,type);
            return ResponseEntity.ok().body(review);
        }catch (Exception e){
            throw new ResourceNotFoundException("Review not found with id " + id);
        }
    }

    @GetMapping("/api/reviews/search/{imdbId}")
    @ApiOperation(value = "This method is used to get the reviews with filters.")
    public ResponseEntity<Page<ReviewType>> searchWithFilters(@PathVariable String imdbId, ReviewPage reviewPage,
                                                       ReviewSearchCriteria reviewSearchCriteria) {
        try {
            Page<ReviewType> reviews = service.getReviewsWithFilters(imdbId,reviewPage, reviewSearchCriteria);
            return ResponseEntity.ok().body(reviews);
        }catch (Exception e){
            throw new ResourceNotFoundException("No existe media con id " + imdbId);
        }
    }


    @RequestMapping(value = "/api/reviews/{imdbId}")
    public ResponseEntity getReviewsbyImbdId(@PathVariable String imdbId) {
        try{
            List<Review> reviews = service.getReviewsFromMediaByImdbId(imdbId);
            return ResponseEntity.ok().body(reviews);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Review not found with id " + imdbId);
        }
    }
}
