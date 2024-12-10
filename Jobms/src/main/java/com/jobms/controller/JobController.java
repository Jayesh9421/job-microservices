package com.jobms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobms.dto.JobDto;
import com.jobms.model.Job;
import com.jobms.service.JobService;


@RestController
@RequestMapping("/jobms")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDto>> findAll() {
    	
    	
        List<JobDto> jobswithCompanyDto = jobService.findAll();
        return new ResponseEntity<>(jobswithCompanyDto, HttpStatus.OK);
    }

    @PostMapping("/job")
    public ResponseEntity<String> createJob(@RequestBody Job job) throws Exception {
        jobService.createJob(job);
//        job.getCompany();
        return new ResponseEntity<>("Job created successfully!", HttpStatus.CREATED);
    }


    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) throws Exception {
        JobDto jobCompanyDto = jobService.getJobById(id);
        if (jobCompanyDto != null) {
            return new ResponseEntity<>(jobCompanyDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) throws Exception {
         jobService.deleteJobById(id);
		return new ResponseEntity<String>("id delete Successfully !!" , HttpStatus.OK);
      
    }
    
    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) throws Exception {
    	 jobService.updateJob(job, id);
    	
        return new ResponseEntity<>("job updated Successfully !! " , HttpStatus.CREATED);
    }
}
