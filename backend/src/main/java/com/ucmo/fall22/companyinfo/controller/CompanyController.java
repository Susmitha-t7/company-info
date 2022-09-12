package com.ucmo.fall22.companyinfo.controller;

import com.ucmo.fall22.companyinfo.dto.CompanyDTO;
import com.ucmo.fall22.companyinfo.dto.CompanySummaryDTO;
import com.ucmo.fall22.companyinfo.model.Company;
import com.ucmo.fall22.companyinfo.model.CompanyMin;
import com.ucmo.fall22.companyinfo.repository.CompanyRepository;
import com.ucmo.fall22.companyinfo.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    //Returns all the companies order by Number Of Employees
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompaniesByNoOfEmployees(){
        try{
            List<CompanyMin> companies;
            companies = companyService.findAll();
            
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

    private CompanyDTO convertToDto(CompanyMin company) {

        //company.getProducts().stream().forEach(x-> System.out.println(x.getName()));
        return modelMapper.map(company, CompanyDTO.class);
    }

    private CompanySummaryDTO convertToOptionalDto(Company company) {
        //company.getProducts().stream().forEach(x-> System.out.println(x.getName()));
        CompanySummaryDTO toRet;
        return toRet = modelMapper.map(company, CompanySummaryDTO.class);
    }

    //Used when clicking company in search result to get all the Company details
    @GetMapping("/companies/{name}")
    public ResponseEntity<CompanySummaryDTO> getCompanyById(@PathVariable("name") String name){
        try{
            CompanySummaryDTO company;
            company = convertToOptionalDto(companyService.findByPermalink(name).orElse(null));
            return ResponseEntity.ok().body(company);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Filter, CategoryCode values
    @GetMapping("/categoryCode")
    public ResponseEntity<List<String>> getAllCategoryCodes(){
        try{
            List<String> categoryCodes;
            categoryCodes = mongoTemplate.query(Company.class).distinct("categoryCode").as(String.class).all();

            if(categoryCodes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(categoryCodes, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Filter, tag values
    @GetMapping("/tags")
    public ResponseEntity<List<String>> getAllTags(){
        try{
            List<String> tagLists;
            tagLists = mongoTemplate.query(Company.class).distinct("tagList").as(String.class).all();

            Set<String> distinctTag = new HashSet<>();
            for(String str: tagLists){
                if(str != null && !str.isEmpty())
                {
                    List<String> strList = Arrays.stream(str.split(",")).map(String::trim).toList();
                    distinctTag.addAll(strList);
                }
            }
            tagLists = distinctTag.stream().limit(20).collect(Collectors.toList());

            if(tagLists.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(tagLists, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tags/{name}")
    public ResponseEntity<List<String>> getTagsBySearch(@PathVariable("name") String name){
        try{
            List<String> tagLists;
            tagLists = mongoTemplate.query(Company.class).distinct("tagList").as(String.class).all();

            Set<String> distinctHash = new HashSet<>();
            for(String str: tagLists){
                if(str != null && !str.isEmpty())
                {
                    List<String> strList = Arrays.stream(str.split(",")).map(String::trim).toList();
                    distinctHash.addAll(strList);
                }
            }
            tagLists = new ArrayList<>(distinctHash);

            List<String> tagSearch = tagLists
                    .stream()
                    .filter(a -> a.toLowerCase().contains(name.toLowerCase())).toList();
            if(tagSearch.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(tagSearch.stream().limit(20).toList(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Filter, Not sure where to use, returns all company name values
    @GetMapping("/companyName")
    public ResponseEntity<List<String>> getAllCompanyNames(){
        try{
            List<String> companyNames;
            companyNames = mongoTemplate.query(Company.class).distinct("name").as(String.class).all();

            if(companyNames.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(companyNames, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Used in funded by and Invested On search box. Returns the companies with %name%
    @GetMapping("/companyName/{name}")
    public ResponseEntity<List<String>> getAllCompanyNamesByName(@PathVariable("name") String name){
        try{
            List<String> companyNames;
            companyNames = mongoTemplate.query(Company.class)
                    .distinct("name")
                    .as(String.class)
                    .all();


            List<String> companySearchNames = companyNames
                                                .stream()
                                                .filter(a -> a.toLowerCase().contains(name.toLowerCase()))
                                                .collect(Collectors.toList());
            if(companySearchNames.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(companySearchNames, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // return all company details as per the filter values and search value
    @RequestMapping(method = RequestMethod.GET, value = "/company/custom")
    public ResponseEntity<List<CompanyDTO>> getCompaniesByFilter(@RequestParam Map<String, String> customQuery) {
        try{
            System.out.println(customQuery);
            List<Company> companies;
            companies = companyService.findAllCompaniesByFilter(customQuery);

            if(companies.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(companies.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList()
                    ), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/competitors")
    public ResponseEntity<List<CompanyDTO>> findAllByPermalink(@RequestParam String[] names) {
        try{

            System.out.println("Requested::"+Arrays.stream(names).toList());
            List<CompanyMin> companies;
            companies = companyService.findAllByPermalink(Arrays.stream(names).toList());

            if(companies.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(companies.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList()
                    ), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
