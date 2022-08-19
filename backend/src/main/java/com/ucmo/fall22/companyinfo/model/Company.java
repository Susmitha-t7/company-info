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
    private String name;
    private String categoryCode;

    @Field("number_of_employees")
    private Integer numberOfEmployees;


    public Company(String name,
                   String categoryCode,
                   Integer numberOfEmployees) {

        this.name = name;
        this.categoryCode = categoryCode;
        this.numberOfEmployees = numberOfEmployees;

    }
}
