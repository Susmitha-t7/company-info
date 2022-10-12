package com.ucmo.fall22.companyinfo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "Company")
public class CompanyMin {

    @Id
    private String id;

    @Field("permalink")
    private String permalink;

    private String name;

    @Field("category_code")
    private String categoryCode;

    @Field("number_of_employees")
    private Integer numberOfEmployees;

    @Field("total_money_raised")
    private String totalMoneyRaised;

    @Field("founded_year")
    private Integer foundedYear;

    @Field("email_address")
    private String emailAddress;

    @Field("phone_number")
    private String phoneNumber;


    public CompanyMin(String name,
                   String permalink,
                   String categoryCode,
                   Integer numberOfEmployees,
                   String totalMoneyRaised,
                   Integer foundedYear,
                   String emailAddress,
                   String phoneNumber) {

        this.name = name;
        this.permalink = permalink;
        this.categoryCode = categoryCode;
        this.numberOfEmployees = numberOfEmployees;
        this.totalMoneyRaised = totalMoneyRaised;
        this.foundedYear = foundedYear;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }
}
