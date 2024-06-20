package com.example.demo.service;

import com.example.demo.entity.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {
    ResponseEntity<Company> createCompany(Company company);
    ResponseEntity<List<Company>> getAllCompanies();
    ResponseEntity<Company> getCompany(Long id);
    ResponseEntity<Company> updateCompany(Long id, Company company);
    ResponseEntity<String> deleteCompany(Long id);
}
