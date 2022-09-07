package com.ucmo.fall22.companyinfo.service;

import com.ucmo.fall22.companyinfo.model.Company;
import com.ucmo.fall22.companyinfo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    public List<Company> findAllByOrderByNumberOfEmployeesDesc() {
        return new ArrayList<>(companyRepository.findAllByOrderByNumberOfEmployeesDesc().stream().limit(200).toList());
    }

    public Optional<Company> findById(String id) {
        return companyRepository.findById(id);
    }

    public List<Company> findAllCompaniesByFilter(Map<String, String> customQuery){
        String search = null,
                categoryCode = null,
                tag= null,
                investedOn= null,
                fundedBy= null,
                numberOfEmployees= null, foundedYear = null;
        if(customQuery.containsKey("search")) search = customQuery.get("search");
        if(customQuery.containsKey("categoryCode")) categoryCode = customQuery.get("categoryCode");
        if(customQuery.containsKey("investedOn")) investedOn = customQuery.get("investedOn");
        if(customQuery.containsKey("tag")) tag = customQuery.get("tag");
        if(customQuery.containsKey("fundedBy")) fundedBy = customQuery.get("fundedBy");
        if(customQuery.containsKey("numberOfEmployees")) numberOfEmployees = customQuery.get("numberOfEmployees");
        if(customQuery.containsKey("foundedYear")) foundedYear = customQuery.get("foundedYear");



        return new ArrayList<>(companyRepository
                                .findAllCompaniesByFilter(search,
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
