package com.compms.controller;

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

import com.compms.model.Company;
import com.compms.service.CompanyService;


@RestController
@RequestMapping("/companies")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	 
	
	@PostMapping("/")
	public ResponseEntity<Company> addCompany(@RequestBody Company company){
		
		Company comp = companyService.addCompany(company);
		
		return new ResponseEntity<Company>(comp , HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Company>> getAllCompanies1()throws Exception{
		
		List<Company> companies = companyService.getAllCompanies();
		
		return new ResponseEntity<>(companies , HttpStatus.OK);
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody Company company , @PathVariable Long id) throws Exception{
		
		companyService.updateCompany(company, id);
	
		return new ResponseEntity<>("companey updated Successfully !!" , HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) throws Exception{ 
		boolean isDeleted = companyService.deleteCompany(id);
		if(isDeleted) {

			return new ResponseEntity<>("Company deleted Successfully" , HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Company not deleted Successfully" , HttpStatus.OK);
			
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id)throws Exception{
		
		Company companyById = companyService.getCompanyById(id);
		
		return new ResponseEntity<Company>(companyById , HttpStatus.OK);
		
		
	}
}
