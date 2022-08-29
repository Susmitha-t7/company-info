package com.ucmo.fall22.companyinfo.controller;

import com.ucmo.fall22.companyinfo.model.Company;
import com.ucmo.fall22.companyinfo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompaniesByNoOfEmployees(){
        try{
            List<Company> companies;
            companies = new ArrayList<>(companyRepository.findAllByOrderByNumberOfEmployeesDesc());
            
            if(companies.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") String id){
        try{
            Optional<Company> company;
            company = companyRepository.findById(id);

            if(company.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return company.map(comp -> ResponseEntity.ok().body(comp))
                    .orElse(ResponseEntity.notFound().build());
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
