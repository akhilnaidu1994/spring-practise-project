package com.akn.springbootfirstproject.service;

import com.akn.springbootfirstproject.model.Course;
import com.akn.springbootfirstproject.model.Review;
import com.akn.springbootfirstproject.repository.CourseRepository;
import com.akn.springbootfirstproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public void addReview(Long courseId, Review review){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Course course = courseOptional.orElseThrow(() -> new RuntimeException("Course not found"));
        review.setCourse(course);
        course.addReview(review);
        reviewRepository.save(review);
    }
}
