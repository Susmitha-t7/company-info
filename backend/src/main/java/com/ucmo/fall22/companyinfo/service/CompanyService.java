package com.ucmo.fall22.companyinfo.service;

import com.ucmo.fall22.companyinfo.model.Company;
import com.ucmo.fall22.companyinfo.model.CompanyMin;
import com.ucmo.fall22.companyinfo.model.News;
import com.ucmo.fall22.companyinfo.repository.CompanyMinRepository;
import com.ucmo.fall22.companyinfo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyMinRepository companyMinRepository;

    public List<CompanyMin> findAll() {
        List<String> companies= new ArrayList<String>();
        companies.add("Google");
        companies.add("Facebook");
        companies.add("YouTube");
        companies.add("Netflix");
        companies.add("Amazon");
        companies.add("PayPal");
        companies.add("Cisco");
        companies.add("Xerox");
        return new ArrayList<>(companyMinRepository.findByNameIn(companies).stream().toList());
    }

    public Optional<Company> findById(String id) {
        return companyRepository.findById(id);
    }

    public List<CompanyMin> findAllByPermalink(List<String> names) {
        return new ArrayList<>(companyMinRepository.findAllByPermalink(names).stream().toList());
    }

    public Optional<Company> findByPermalink(String name) {
        return companyRepository.findFirstByPermalink(name);
    }

    public List<CompanyMin> findAllCompaniesByFilter(Map<String, String> customQuery){
        String search = null,
                searchStart = null,
                categoryCode = null,
                tag= null,
                investedOn= null,
                fundedBy= null,
                numberOfEmployees= null,
                foundedYear = null;
        if(customQuery.containsKey("search")) search = customQuery.get("search");
        if(customQuery.containsKey("searchStart")) searchStart = customQuery.get("searchStart");
        if(customQuery.containsKey("categoryCode")) categoryCode = customQuery.get("categoryCode");
        if(customQuery.containsKey("investedOn")) investedOn = customQuery.get("investedOn");
        if(customQuery.containsKey("tag")) tag = customQuery.get("tag");
        if(customQuery.containsKey("fundedBy")) fundedBy = customQuery.get("fundedBy");
        if(customQuery.containsKey("numberOfEmployees")) numberOfEmployees = customQuery.get("numberOfEmployees");
        if(customQuery.containsKey("foundedYear")) foundedYear = customQuery.get("foundedYear");



        return new ArrayList<>(companyRepository
                                .findAllCompaniesByFilter(search,
                                        searchStart,
                                                            categoryCode,
                                                            investedOn,
                                                            tag,
                                                            fundedBy,
                                                            numberOfEmployees,
                                                            foundedYear)
                                .stream()
                                .limit(200)
                                .toList());

    }


}
