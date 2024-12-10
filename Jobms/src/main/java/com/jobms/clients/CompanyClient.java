package com.jobms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jobms.external.Company;

@FeignClient(name = "COMPANYMS")
public interface CompanyClient {
	
	@GetMapping("/companies/{id}")
	Company getCompany(@PathVariable("id") Long id);
	
}