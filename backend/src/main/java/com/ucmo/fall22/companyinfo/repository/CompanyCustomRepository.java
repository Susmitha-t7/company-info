package com.ucmo.fall22.companyinfo.repository;

import com.ucmo.fall22.companyinfo.model.Company;

import java.util.List;

public interface CompanyCustomRepository {
    List<Company> findCompaniesByProperties(String search,
                                                   String categoryCode,
                                                   Integer numberOfEmployees,
                                                   Integer foundedYear,
                                                   String tag,
                                                   String investedOn,
                                                   String FundedBy);

    List<Company> findAllCompaniesByFilter(String search,
                                           String categoryCode,
                                           String investedOn,
                                           String tag,
                                           String fundedBy,
                                           String numberOfEmployees,
                                           String foundedYear
                                           );

}
