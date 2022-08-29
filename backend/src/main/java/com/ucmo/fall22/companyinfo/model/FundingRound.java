package com.ucmo.fall22.companyinfo.model;

import java.util.List;

public class FundingRound {

    private Integer id;

    private String roundCode;

    private String sourceUrl;

    private String sourceDescription;

    private Integer raisedAmount;

    private String raisedCurrencyCode;

    private Integer fundedYear;

    private Integer fundedMonth;

    private Integer fundedDay;

    private List<Investment> investmentList;
}
