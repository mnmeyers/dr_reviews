package com.dr_reviews.demo.service;

import com.dr_reviews.demo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;//fix this

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired ReviewRepository reviewRepository;
    // For Production code, I would make sure to have a hash function for all db ids,
    // and only send hashed ids to the frontend and convert them back to numeric ids before querying db
    @RequestMapping(method = RequestMethod.GET, value = "/reviews/{id}")
    public @ResponseBody Review getReviewById(@PathVariable Integer id) {
        return reviewRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/reviews")
    public @ResponseBody Review createOrUpdateReview(@RequestBody Review review) {
        return reviewRepository.updateOrCreateReview(review);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/reviews/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Integer id) {
        //need to validate that created by user is the same as deleting user, would do this via the session
        reviewRepository.deleteReview(id);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/reviews")
    public List<Review> getReviewsByDoctorId(@RequestParam Integer docId) {
        return reviewRepository.findReviewsByDoctorId(docId);
    }

    

}
