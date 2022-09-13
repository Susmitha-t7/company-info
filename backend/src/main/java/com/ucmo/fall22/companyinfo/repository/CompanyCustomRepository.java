package com.ucmo.fall22.companyinfo.repository;

import com.ucmo.fall22.companyinfo.model.Company;
import com.ucmo.fall22.companyinfo.model.CompanyMin;

import java.util.List;

public interface CompanyCustomRepository {

    List<CompanyMin> findAllCompaniesByFilter(String search,
                                           String searchStart,
                                           String categoryCode,
                                           String investedOn,
                                           String tag,
                                           String fundedBy,
                                           String numberOfEmployees,
                                           String foundedYear
                                           );

    List<CompanyMin> findAllByPermalink(List<String> name);

}
