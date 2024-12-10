package com.jobms.service.serviceIML;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobms.dto.JobDto;
import com.jobms.external.Company;
import com.jobms.external.Review;
import com.jobms.mapper.JobMapper;
import com.jobms.model.Job;
import com.jobms.repo.JobRepository;
import com.jobms.service.JobService;

@Service
public class JobServiceIMPL implements JobService {
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	RestTemplate restTemplate;

//	@Autowired
//	private CompanyRepository companyRepository;

	@Override
	public List<JobDto> findAll() {

		List<Job> jobs = jobRepository.findAll();
		List<JobDto> jobWithCompaniesDtos = new ArrayList<>();

		// RestTemplate restTemplate = new RestTemplate();

		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private JobDto convertToDto(Job job) {

		Company company = restTemplate.getForObject("https://COMPANYMS/companies/" + job.getCompanyId(), Company.class);
		ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
				"http://REVIEWMS/reviews/company?CompanyId" + job.getCompanyId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Review>>() {
				});
		List<Review> reviews = reviewResponse.getBody();
		JobDto jobDto = JobMapper.mapToJobWithCompanyDto(job, company,reviews);
//		jobDto.setCompany(company);
		return jobDto;
	}

	@Override
	public void createJob(Job job) throws Exception {
		// Check if the company object is present in the request
//		 if (job.getCompany() == null || Objects.isNull(job.getCompany().getId())) {
//		        throw new Exception("Company information is missing in the request.");
//		    }
//
//	    // Fetch the existing company from the database using the provided ID
//	    Long companyId = job.getCompany().getId();
//	    Company existingCompany = companyRepository.findById(companyId)
//	            .orElseThrow(() -> new Exception("Company with ID " + companyId + " not found."));
//
//	    // Set the existing company to the job object
//	    job.setCompany(existingCompany);

		// Save the job to the database
		jobRepository.save(job);
	}

	@Override
	public JobDto getJobById(Long id) throws Exception {

		Job job = jobRepository.findById(id).orElseThrow(() -> new Exception("This id is Not found"));

		return convertToDto(job);
	}

	@Override
	public void deleteJobById(Long id) throws Exception {

		Job existingjob = jobRepository.findById(id).orElseThrow(() -> new Exception("This id is Not found"));

		jobRepository.deleteById(existingjob.getId());
	}

	@Override
	public void updateJob(Job job, Long id) throws Exception {
		Job existingjob = jobRepository.findById(id).orElseThrow(() -> new Exception("This id is Not found"));

		existingjob.setTitle(job.getTitle());
		existingjob.setDescription(job.getDescription());
		existingjob.setLocation(job.getLocation());
		existingjob.setMinSalary(job.getMinSalary());
		existingjob.setMaxSalary(job.getMaxSalary());

		jobRepository.save(existingjob);

	}

}
