package com.ucmo.fall22.companyinfo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class CompanyDetail {

    @Id
    private String id;

    private String name;

    private String permalink;

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

    @Field("founded_year")
    private Integer foundedYear;

    @Field("founded_month")
    private Integer foundedMonth;

    @Field("founded_day")
    private Integer foundedDay;

    @Field("tag_list")
    private String tagList;

    private List<String> tags;

    @Field("email_address")
    private String emailAddress;

    @Field("phone_number")
    private String phoneNumber;

    @Field("description")
    private String description;

    @Field("overview")
    private String overview;

    private List<CompanyDepend> products;

    private List<Relationship> relationships;

    private List<CompanyDepend> competitions;

    @Field("total_money_raised")
    private String totalMoneyRaised;

    private List<FundingRound> fundingRounds;

    private List<FundingRound> investments;

    private Acquisition acquisition;

    private List<Acquisition> acquisitions;

    private List<Office> offices;

}
