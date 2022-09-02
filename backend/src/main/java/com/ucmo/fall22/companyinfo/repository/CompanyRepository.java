package com.ucmo.fall22.companyinfo.repository;

import com.ucmo.fall22.companyinfo.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {

    Optional<Company> findCompanyByName(String name);
    @Query(fields = "{'id' : 1, " +
                    "'name' : 1 , " +
                    "'total_money_raised' : 1, " +
                    "'permalink' : 1 , " +
                    "'category_code' :1, " +
                    "'number_of_employees' : 1 , " +
                    "'founded_year' :1 , " +
                    "'email_address' : 1, " +
                    "'phone_number' : 1}")
    List<Company> findAllByOrderByNumberOfEmployeesDesc();

    Optional<Company> findById(String Id);

 }
