package com.ucmo.fall22.companyinfo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Acquisition {

    @Field(value = "price_amount")
    private String priceAmount;

    @Field(value = "price_currency_code")
    private String priceCurrencyCode;

    @Field(value = "term_code")
    private String termCode;

    @Field(value= "source_url")
    private String sourceUrl;

    @Field(value= "source_description")
    private String sourceDesc;

    @Field(value = "acquired_year")
    private Integer acquiredYear;

    @Field(value = "acquired_month")
    private Integer acquiredMonth;

    @Field(value = "acquired_day")
    private Integer acquiredDay;

    @Field(value = "company")
    private NameDepend company;

    @Field(value = "acquiring_company")
    private NameDepend acquiringCompany;

}
