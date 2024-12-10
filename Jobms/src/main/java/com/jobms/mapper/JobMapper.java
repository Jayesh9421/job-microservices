package com.jobms.mapper;

import java.util.List;

import com.jobms.dto.JobDto;
import com.jobms.external.Company;
import com.jobms.external.Review;
import com.jobms.model.Job;

public class JobMapper {

	public static JobDto mapToJobWithCompanyDto(Job job, Company company,List<Review> reviews) {
		JobDto jobWithCompanyDto =  new JobDto();
		jobWithCompanyDto.setId(job.getId());
		jobWithCompanyDto.setTitle(job.getTitle());
		jobWithCompanyDto.setDescription(job.getDescription());
		jobWithCompanyDto.setLocation(job.getLocation());
		jobWithCompanyDto.setMaxSalary(job.getMaxSalary());
		jobWithCompanyDto.setMinSalary(job.getMinSalary());
		jobWithCompanyDto.setCompany(company);
		jobWithCompanyDto.setReviews(reviews);
		
		return jobWithCompanyDto;
	}
	
	
}
