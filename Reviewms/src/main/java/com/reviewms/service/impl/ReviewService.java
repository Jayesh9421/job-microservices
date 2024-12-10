package com.reviewms.service.impl;

import java.util.List;

import com.reviewms.model.Review;


public interface ReviewService {

	List<Review> getAllReviews(Long companyId); 
	void addReview(Long companyId , Review review) throws Exception;
	Review getReview( Long reviewId) throws Exception;
	Review updateReview( Long reviewId , Review review) throws Exception;
	void deleteReview(Long reviewId) throws Exception;
	 List<Review> getReviewsByCompanyId(Long companyId);
}
