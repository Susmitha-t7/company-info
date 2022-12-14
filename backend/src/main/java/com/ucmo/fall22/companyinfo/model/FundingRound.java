package com.ucmo.fall22.companyinfo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class FundingRound {

    @Field(value = "id")
    private String id;
    @Field(value = "round_code")
    private String roundCode;

    @Field(value = "source_url")
    private String sourceUrl;

    @Field(value = "source_description")
    private String sourceDescription;

    @Field(value = "raised_amount")
    private Long raisedAmount;

    @Field(value = "raised_currency_code")
    private String raisedCurrencyCode;

    @Field(value = "funded_year")
    private Integer fundedYear;

    @Field(value = "funded_month")
    private Integer fundedMonth;

    @Field(value = "funded_day")
    private Integer fundedDay;

    @Field(value = "company")
    private NameDepend company;

    @Field(value = "investments")
    private List<Investment> investments;
}
