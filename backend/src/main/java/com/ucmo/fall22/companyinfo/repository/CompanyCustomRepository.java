package com.ucmo.fall22.companyinfo.repository;

import com.ucmo.fall22.companyinfo.model.Company;

import java.util.List;

public interface CompanyCustomRepository {

    List<Company> findAllCompaniesByFilter(String search,
                                           String searchStart,
                                           String categoryCode,
                                           String investedOn,
                                           String tag,
                                           String fundedBy,
                                           String numberOfEmployees,
                                           String foundedYear
                                           );

}
