package com.compms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compms.model.Company;


public interface CompanyRepository extends JpaRepository<Company, Long>{

}
