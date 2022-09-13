package com.ucmo.fall22.companyinfo.dto;

import com.ucmo.fall22.companyinfo.model.*;
import lombok.Data;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class CompanySummaryDTO {


 private String id;
 private String name;
 private String permalink;
 private String totalMoneyRaised;
 private String categoryCode;
 private Integer foundedYear;
 private String emailAddress;
 private String phoneNumber;
    private String description;
    private String overview;
    private String crunchbaseUrl;
    private String homepageUrl;
    private String blogUrl;
    private String blogFeedUrl;
    private String twitterUsername;
    private Integer numberOfEmployees;
    private Integer foundedMonth;
    private Integer foundedDay;
    private String tagList;
    private List<NameDepend> products;
    private List<Office> offices;
    private List<Competitor> competitions;
    private List<Investment> investments;
    private List<Acquisition> acquisitions;
    private Acquisition acquiredBy;
    private List<FundingRound> fundingRounds;
    private List<Relationship> relationships;
}
