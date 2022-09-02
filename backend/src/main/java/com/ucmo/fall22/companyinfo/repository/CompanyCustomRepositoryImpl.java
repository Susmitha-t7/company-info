package com.ucmo.fall22.companyinfo.repository;

import com.ucmo.fall22.companyinfo.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyCustomRepositoryImpl implements CompanyCustomRepository{

    @Autowired
    private final MongoTemplate mongoTemplate;

    public CompanyCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Company> findCompaniesByProperties(String search,
                                                   String categoryCode,
                                                   Integer numberOfEmployees,
                                                   Integer foundedYear,
                                                   String tag,
                                                   String investedOn,
                                                   String fundedBy) {

        final List<Criteria> criterias = new ArrayList<>();
        if(search !=null && !search.isEmpty())  criterias.add(Criteria.where("name").regex(search));
        if(categoryCode !=null && !categoryCode.isEmpty())  criterias.add(Criteria.where("category_code").is(categoryCode));
        if(tag !=null && !tag.isEmpty())  criterias.add(Criteria.where("tag_list").regex(tag));
        if(fundedBy !=null && !fundedBy.isEmpty())  {
            criterias.add(Criteria.where("funding_rounds.investments.company.name").is(fundedBy));
            criterias.add(Criteria.where("funding_rounds.investments.financial_org.name").is(fundedBy));
            criterias.add(Criteria.where("funding_rounds.investments.person.name").is(fundedBy));
        }
        if(numberOfEmployees !=null)  criterias.add(Criteria.where("number_of_employees").is(numberOfEmployees));
        if(foundedYear !=null)  criterias.add(Criteria.where("founded_year").is(foundedYear));
        if(investedOn !=null && !investedOn.isEmpty())  criterias.add(Criteria.where("investments.funding_round.company.name").is(investedOn));

        Criteria criteria = new Criteria().andOperator(criterias.toArray(new Criteria[0]));

        final Query searchQuery = new Query(criteria);

        return mongoTemplate.find(searchQuery, Company.class);
    }

}
