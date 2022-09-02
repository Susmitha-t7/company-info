package com.ucmo.fall22.companyinfo.dto;

import com.ucmo.fall22.companyinfo.model.NameDepend;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class CompanyDTO {
    private String id;
    private String name;
    private String totalMoneyRaised;
    private String categoryCode;
    private Integer foundedYear;
    private String emailAddress;
    private String phoneNumber;
}
