package com.ucmo.fall22.companyinfo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class CompanyDetail {


    private List<String> tags;

    private List<NameDepend> products;

    private List<Relationship> relationships;

    private List<NameDepend> competitions;

    private List<FundingRound> fundingRounds;

    private List<FundingRound> investments;

    private Acquisition acquisition;

    private List<Acquisition> acquisitions;

    private List<Office> offices;

}


/*
{
   name: "Name",
   lastName: "Last Name",
   attributes: {
      age: 25
      eye: {
         color: "RED",
         size: "BIG"
      }
   }
}

@Query(value = "{'attributes.eye.color' : ?0}")
List<User> findAllByAttributesEyeColor(String color);

// It will return the users with only name and last name
@Query(value = "{'attributes.age' : ?0}", fields = "{ name : 1, lastName : 1 }")
List<User> findAllByAttributesAge(int age);

 */