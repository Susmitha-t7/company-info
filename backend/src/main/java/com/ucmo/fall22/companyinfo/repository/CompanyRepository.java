package com.ucmo.fall22.companyinfo.repository;

import com.ucmo.fall22.companyinfo.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {

    Optional<Company> findCompanyByName(String name);
    List<Company> findAllByOrderByNumberOfEmployeesDesc();
}
