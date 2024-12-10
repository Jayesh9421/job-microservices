package com.jobms.service;

import java.util.List;

import com.jobms.dto.JobDto;
import com.jobms.model.Job;


public interface JobService {

	List<JobDto> findAll();
	void createJob(Job job ) throws Exception;
	JobDto getJobById(Long id) throws Exception;
	void deleteJobById(Long id) throws Exception;
	void updateJob(Job job, Long id) throws Exception;
	
}
