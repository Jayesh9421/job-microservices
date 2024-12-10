package com.reviewms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewms.model.Review;
import com.reviewms.repo.ReviewRepository;


@Service
public class ReviewServiceIMPL implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews;
	}

	@Override
	public void addReview(Long companyId, Review review) throws Exception {


		if (companyId != null) {
			review.setCompanyId(companyId);
			reviewRepository.save(review);
		}
	}

	@Override
	public Review getReview(Long reviewId) throws Exception {
	    // Retrieve the review using the repository
	    return reviewRepository.findById(reviewId)
	            .orElseThrow(() -> new Exception("Review not found with ID: " + reviewId));
	}


	@Override
	public Review updateReview(Long reviewId, Review review) throws Exception {
	    // Retrieve the existing review using the repository
	    Review existingReview = reviewRepository.findById(reviewId)
	            .orElseThrow(() -> new Exception("Review not found with ID: " + reviewId));

	    // Update the review fields
	    existingReview.setTitle(review.getTitle());
	    existingReview.setDescription(review.getDescription());
	    existingReview.setRating(review.getRating());

	    // Save the updated review
	    return reviewRepository.save(existingReview);
	}
	
	@Override
	public void deleteReview(Long reviewId) throws Exception {
	    // Check if the review exists
	    Review existingReview = reviewRepository.findById(reviewId)
	            .orElseThrow(() -> new Exception("Review not found with ID: " + reviewId));

	    // Delete the review
	    reviewRepository.delete(existingReview);
	}

	@Override
	public List<Review> getReviewsByCompanyId(Long companyId) {
		if(companyId != null) {
			return reviewRepository.findByCompanyId(companyId);
		}else {
			  return reviewRepository.findAll();
		}

	}
	
	





}
