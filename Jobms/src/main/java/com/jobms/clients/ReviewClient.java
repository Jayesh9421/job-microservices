package com.jobms.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.jobms.external.Review;

@FeignClient(name = "REVIEWMS")
public interface ReviewClient {

	// "http://REVIEWMS/reviews/company?CompanyId" 
	@GetMapping("/reviews")
	List<Review> getReviews(@RequestParam("companyId") Long companyId);
	
}
