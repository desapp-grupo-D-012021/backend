package ar.edu.unq.desapp.grupoD.backenddesapptp.webService;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Review;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import ar.edu.unq.desapp.grupoD.backenddesapptp.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl service;

    @RequestMapping("/hello")
    public String sayHi(){
        return "hello";
    }

    @GetMapping
    public List<ReviewType> allReviews() {
        return service.findAll();
    }

    @PostMapping
    public void addReview(@RequestBody Review review){
        service.addReview(review);
    }

    @PostMapping("/reviews/{id}")
    public void rateAReviewPositively(@RequestBody Integer id){
        service.rateAReviewPositevely(id);
    }
}
