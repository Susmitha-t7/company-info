package com.ucmo.fall22.companyinfo.controller;

import com.ucmo.fall22.companyinfo.dto.CompanyDTO;
import com.ucmo.fall22.companyinfo.dto.CompanySummaryDTO;
import com.ucmo.fall22.companyinfo.model.Company;
import com.ucmo.fall22.companyinfo.repository.CompanyRepository;
import com.ucmo.fall22.companyinfo.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompaniesByNoOfEmployees(){
        try{
            List<Company> companies;
            companies = companyService.findAllByOrderByNumberOfEmployeesDesc();
            
            if(companies.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(companies.stream()
                                                .map(this::convertToDto)
                                                .collect(Collectors.toList()
                                                ), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private CompanyDTO convertToDto(Company company) {

        //company.getProducts().stream().forEach(x-> System.out.println(x.getName()));
        return modelMapper.map(company, CompanyDTO.class);
    }

    private CompanySummaryDTO convertToOptionalDto(Company company) {
        //company.getProducts().stream().forEach(x-> System.out.println(x.getName()));
        CompanySummaryDTO toRet = modelMapper.map(company, CompanySummaryDTO.class);
        toRet.setCompanyDTO(modelMapper.map(company, CompanyDTO.class));
        return toRet;
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanySummaryDTO> getCompanyById(@PathVariable("id") String id){
        try{
            CompanySummaryDTO company;
            company = convertToOptionalDto(companyService.findById(id).orElse(null));
            return ResponseEntity.ok().body(company);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
