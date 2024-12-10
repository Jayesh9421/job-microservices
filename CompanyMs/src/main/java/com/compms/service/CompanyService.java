package com.compms.service;

import java.util.List;

import com.compms.model.Company;

public interface CompanyService {

	Company getCompanyById(Long id) throws Exception;
	Company addCompany(Company company);
	List<Company> getAllCompanies();
	Company updateCompany(Company company , Long id) throws Exception;
	boolean deleteCompany(Long id) throws Exception;
	
}
