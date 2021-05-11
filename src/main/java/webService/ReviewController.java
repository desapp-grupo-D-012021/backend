package webService;

import model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ReviewServiceImpl;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewServiceImpl service;

    @GetMapping("/api/reviews")
    public List<Review> allReviews(){
        List<Review> reviews = service.findAll();
        return reviews;
    }
}
