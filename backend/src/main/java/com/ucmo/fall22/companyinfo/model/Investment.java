package com.ucmo.fall22.companyinfo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Investment {

    @Field(value = "funding_round")
    private FundingRound fundingRound;
}
