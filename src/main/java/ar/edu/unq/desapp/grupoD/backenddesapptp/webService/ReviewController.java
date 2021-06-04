package ar.edu.unq.desapp.grupoD.backenddesapptp.webService;

import ar.edu.unq.desapp.grupoD.backenddesapptp.exceptions.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Review;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.util.List;


@RestController
@EnableAutoConfiguration
public class ReviewController {

    @Autowired
    private ReviewServiceImpl service;

    @RequestMapping("/hello")
    public String sayHi(){
        return "hello";
    }

    @GetMapping("/reviews")
    public List<ReviewType> allReviews() {
        return service.findAll();
    }


    @RequestMapping(value = "/reviews/{id}")
    public ResponseEntity<? extends Serializable> getReviewbyId(@PathVariable Integer id) {
        try{
            ReviewType review = service.getReview(id);
            return ResponseEntity.ok().body(review);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Review not found with id " + id);
        }
    }

    private ResponseEntity<String> reviewNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
    }

    @PostMapping("/reviews")
    public ResponseEntity addReview(@RequestBody Review review){
        service.addReview(review);
        return ResponseEntity.ok().body(review);
    }

    @PatchMapping("/reviews/like/{id}")
    public ResponseEntity<? extends Serializable> rateAReviewPositively(@PathVariable Integer id){
       try{
           ReviewType review = service.rateAReviewPositevely(id);
           return ResponseEntity.ok().body(review);
       }catch (Exception e){
           throw new ResourceNotFoundException("Review not found with id " + id);
       }

    }

    @PatchMapping("/reviews/dislike/{id}")
    public ResponseEntity<? extends Serializable> rateAReviewNegatively(@PathVariable Integer id){
        try{
            ReviewType review = service.rateAReviewNegatively(id);
            return ResponseEntity.ok().body(review);
        }catch (Exception e){
            throw new ResourceNotFoundException("Review not found with id " + id);
        }
    }

}
