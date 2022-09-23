package com.ucmo.fall22.companyinfo.repository;

import com.ucmo.fall22.companyinfo.model.Company;
import com.ucmo.fall22.companyinfo.model.CompanyMin;
import com.ucmo.fall22.companyinfo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class CompanyCustomRepositoryImpl implements CompanyCustomRepository{

    @Autowired
    private final MongoTemplate mongoTemplate;

    public CompanyCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<CompanyMin> findAllCompaniesByFilter(String search,
                                                  String searchStart,
                                                  String categoryCode,
                                                  String investedOn,
                                                  String tag,
                                                  String fundedBy,
                                                  String numberOfEmployees,
                                                  String foundedYear) {



        Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if(search !=null && !search.isEmpty() && !search.equals("undefined"))  criteria.add(where("name").regex(search));
        else if(searchStart !=null && !searchStart.isEmpty() && !searchStart.equals("undefined")){
            criteria.add(where("name").regex("^" + searchStart));
        }
        if(categoryCode !=null && !categoryCode.isEmpty() && !categoryCode.equals("undefined"))  criteria.add(where("category_code").is(categoryCode));
        if(investedOn !=null && !investedOn.isEmpty() && !investedOn.equals("undefined"))  criteria.add(where("investments.funding_round.company.name").is(investedOn));
        if(tag !=null && !tag.isEmpty() && !tag.equals("undefined"))  criteria.add(where("tag_list").regex(tag));
        if(fundedBy !=null && !fundedBy.isEmpty() && !fundedBy.equals("undefined")) {
            Criteria criteria1 = new Criteria();
            criteria1.orOperator(where("funding_rounds.investments.company.name").is(fundedBy),
                                where("funding_rounds.investments.financial_org.name").is(fundedBy),
                                where("funding_rounds.investments.person.first_name").is(fundedBy),
                                where("funding_rounds.investments.person.last_name").is(fundedBy));
            criteria.add(criteria1);
           // criteria.add(Criteria.where("funding_rounds.investments.company.name").is(fundedBy)
            //        .orOperator(Criteria.where("funding_rounds.investments.financial_org.name").is(fundedBy)));
        }
        if(numberOfEmployees !=null && !numberOfEmployees.isEmpty() && !numberOfEmployees.equals("undefined")){
            int numberOfEmpFrom = 0, numberOfEmpTo = 0;
            if(numberOfEmployees.contains("than")){
                criteria.add(where("number_of_employees").gt(10000));
            }
            else {
                String[] splitNum = numberOfEmployees.split("-");
                numberOfEmpFrom = Integer.parseInt(splitNum[0].trim());
                numberOfEmpTo = Integer.parseInt(splitNum[1].trim());
                criteria.add(where("number_of_employees").lt(numberOfEmpTo).gt(numberOfEmpFrom));
            }
        }
        if(foundedYear !=null && !foundedYear.isEmpty() && !foundedYear.equals("undefined") && !foundedYear.equals("0"))  criteria.add(where("founded_year").is(Integer.parseInt(foundedYear)));

        System.out.println("Search Text: "+ search
                            + "searchStart: "+ searchStart
                            + "  categoryCode: "+ categoryCode
                            + "  investedOn: "+ investedOn
                            + "  tag: "+ tag
                            + "  fundedBy: "+ fundedBy
                            + "  numberOfEmployees: "+ numberOfEmployees
                            + "  foundedYear: "+ foundedYear+"Criteria : "+criteria);


    if(criteria.size()>0){
        System.out.println("In this");
        Criteria cri = new Criteria().andOperator(criteria.toArray(new Criteria[0]));

        final Query searchQuery = new Query(cri);

        return mongoTemplate.find(searchQuery, CompanyMin.class);
    }

    else{
        Query query1 = new Query();
        List<String> companies= new ArrayList<String>();
        companies.add("Google");
        companies.add("Facebook");
        companies.add("YouTube");
        companies.add("Netflix");
        companies.add("Amazon");
        companies.add("PayPal");
        companies.add("Cisco");
        companies.add("Xerox");
        //CompanyMinRepository companyMinRepository = null;
       // return new ArrayList<>(companyMinRepository.findByNameIn(companies).stream().toList());

        return mongoTemplate.query(CompanyMin.class)
                .matching(query(where("name").in(companies)))
                .all();
        //return mongoTemplate.find(query1, CompanyMin.class);
    }

    }

    @Override
    public List<CompanyMin> findAllByPermalink(List<String> name) {
        Query query = new Query();
        query.addCriteria(where("permalink").in(name));
        return mongoTemplate.find(query, CompanyMin.class);
    }


}
