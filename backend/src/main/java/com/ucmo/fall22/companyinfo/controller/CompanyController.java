package com.ucmo.fall22.companyinfo.controller;

import com.ucmo.fall22.companyinfo.model.Company;
import com.ucmo.fall22.companyinfo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    CompanyRepository repository;

    @GetMapping("/home")
    public ResponseEntity<List<Company>> getAllCompanies(){
        try{
            List<Company> companies;
            companies = new ArrayList<>(repository.findAll());
            if(companies.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompaniesByNoOfEmployees(){
        try{
            List<Company> companies;
            companies = new ArrayList<>(repository.findAllByOrderByNumberOfEmployeesDesc());
            
            if(companies.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
