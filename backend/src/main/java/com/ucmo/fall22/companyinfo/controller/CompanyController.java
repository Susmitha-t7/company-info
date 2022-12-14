package com.ucmo.fall22.companyinfo.controller;

import com.ucmo.fall22.companyinfo.dto.CompanyDTO;
import com.ucmo.fall22.companyinfo.dto.CompanySummaryDTO;
import com.ucmo.fall22.companyinfo.model.Company;
import com.ucmo.fall22.companyinfo.model.CompanyMin;
import com.ucmo.fall22.companyinfo.model.News;
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
import org.springframework.web.client.RestTemplate;

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
    //Angular service: baseUrl
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
    // Angular service: companyDetailURL
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
    // Angular service: categoryCode
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
    // Angular service: tagURL
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



    // return all company details as per the filter values and search value
    // Angular service: customFilter
    @RequestMapping(method = RequestMethod.GET, value = "/company/custom")
    public ResponseEntity<List<CompanyDTO>> getCompaniesByFilter(@RequestParam Map<String, String> customQuery) {
        try{
            System.out.println(customQuery);
            List<CompanyMin> companies;
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

    // Angular service: competitorURL
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

    // Angular Service: newsURL
    @GetMapping("/news")
    public ResponseEntity<?> getNews(){
        try {
            String uri = "https://newsapi.org/v2/top-headlines?country=us&sortBy=publishedAt&pageSize=3&category=business&apiKey=c06ecca1df4a417a89f9425f4d714322";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // // Angular Service: newsURL
    @GetMapping("/news/{name}")
    public ResponseEntity<?> getNewsByName(@PathVariable("name") String name){
        try {
            String uri = "https://newsapi.org/v2/everything?q="+name+"&sortBy=publishedAt&language=en&pageSize=3&apiKey=c06ecca1df4a417a89f9425f4d714322";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
