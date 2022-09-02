package com.ucmo.fall22.companyinfo.service;

import com.ucmo.fall22.companyinfo.model.Company;
import com.ucmo.fall22.companyinfo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
