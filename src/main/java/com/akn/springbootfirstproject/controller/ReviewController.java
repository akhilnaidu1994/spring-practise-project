package com.akn.springbootfirstproject.controller;

import com.akn.springbootfirstproject.model.Review;
import com.akn.springbootfirstproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/courses/{courseId}")
    public void addReview(@PathVariable("courseId") Long courseId, @RequestBody Review review){
        reviewService.addReview(courseId,review);
    }
}
