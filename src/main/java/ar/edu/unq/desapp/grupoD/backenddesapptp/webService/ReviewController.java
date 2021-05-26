package ar.edu.unq.desapp.grupoD.backenddesapptp.webService;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Review;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Optional<ReviewType> getReviewbyId(@PathVariable Integer id){
        return service.getReview(id);
    }

    @PostMapping("/reviews")
    public void addReview(@RequestBody Review review){
        service.addReview(review);
    }

    @PutMapping("/reviews/{id}")
    public void rateAReviewPositively(@PathVariable Integer id){
        service.rateAReviewPositevely(id);
    }

}
