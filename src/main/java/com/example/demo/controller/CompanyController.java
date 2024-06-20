package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>>findAll(){
        return companyService.getAllCompanies();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company>getById(@PathVariable Long id){
        return companyService.getCompany(id);
    }
    @PostMapping
    public ResponseEntity<Company>CreateCompany(@RequestBody Company company){
        return companyService.createCompany(company);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Company> editCompany(@PathVariable Long id, @RequestBody Company company){
        return companyService.updateCompany(id,company);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        return companyService.deleteCompany(id);
    }
}
