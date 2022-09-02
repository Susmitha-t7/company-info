package com.ucmo.fall22.companyinfo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "company")
public class Company {

    @Id
    private String id;

    private String permalink;
    private String name;

    @Field("crunchbase_url")
    private String crunchbaseUrl;

    @Field("homepage_url")
    private String homepageUrl;

    @Field("blog_url")
    private String blogUrl;

    @Field("blog_feed_url")
    private String blogFeedUrl;

    @Field("twitter_username")
    private String twitterUsername;
    @Field("category_code")
    private String categoryCode;

    @Field("number_of_employees")
    private Integer numberOfEmployees;

    @Field("total_money_raised")
    private String totalMoneyRaised;

    @Field("founded_year")
    private Integer foundedYear;

    @Field("founded_month")
    private Integer foundedMonth;

    @Field("founded_day")
    private Integer foundedDay;

    @Field("tag_list")
    private String tagList;
    private String description;

    private String overview;

    @Field("email_address")
    private String emailAddress;

    @Field("phone_number")
    private String phoneNumber;

    @Field(value = "products")
    private List<NameDepend> products;

    public Company(String name,
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
