package com.example.demo.service.impl;

import com.example.demo.entity.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public ResponseEntity<Company> createCompany(Company company) {
        Company savedCompany = companyRepository.save(company);
        return new ResponseEntity<>(savedCompany, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Company> getCompany(Long id) {
        Optional<Company> findCompany = companyRepository.findById(id);
        return findCompany.map(company -> new ResponseEntity<>(company, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @Override
    public ResponseEntity<Company> updateCompany(Long id, Company company) {
        Optional<Company> existingCompanyOpt = companyRepository.findById(id);
        if (existingCompanyOpt.isPresent()) {
            Company existingCompany = existingCompanyOpt.get();
            existingCompany.setDescription(company.getDescription());
            existingCompany.setName(company.getName());
            existingCompany.setJobs(company.getJobs());

            Company updatedCompany = companyRepository.save(existingCompany);
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return new ResponseEntity<>("Company deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job not found!", HttpStatus.NOT_FOUND);
        }
    }
}
