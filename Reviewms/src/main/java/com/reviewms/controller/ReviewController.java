package com.reviewms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewms.model.Review;
import com.reviewms.service.impl.ReviewService;

@RestController
@RequestMapping("/reviews") // Changed to make the review endpoint independent of companyId
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Fetch all reviews for a specific company, or all reviews if no companyId is provided
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam(required = false) Long companyId) {
        List<Review> reviews;
        if (companyId != null) {
            reviews = reviewService.getAllReviews(companyId); // Filter by companyId
        } else {
            reviews = reviewService.getAllReviews(null); // Fetch all reviews if no companyId
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Add a new review, companyId is now passed in the request body or as part of the review
    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review) throws Exception {
        reviewService.addReview(review.getCompanyId(), review);
        return new ResponseEntity<>("Review added successfully!", HttpStatus.CREATED);
    }

    // Get review by ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) throws Exception {
        Review review = reviewService.getReview(reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // Update a review by reviewId
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) throws Exception {
        reviewService.updateReview(reviewId, review);
        return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
    }
    @GetMapping("/company")
    public ResponseEntity<List<Review>> getReviews(@RequestParam(required = false) Long CompanyId){
    	 
    	List<Review> reviewsByCompanyId = reviewService.getReviewsByCompanyId(CompanyId);
    	
    	return new ResponseEntity<>(reviewsByCompanyId,HttpStatus.OK);
    }
}
