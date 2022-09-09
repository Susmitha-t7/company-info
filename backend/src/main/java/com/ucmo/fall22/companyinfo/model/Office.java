package com.ucmo.fall22.companyinfo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Office {

    private String description;
    private String address1;

    private String address2;

    @Field("zip_code")
    private String zipCode;

    private String city;

    @Field("state_code")
    private String stateCode;

    @Field("country_code")
    private String countryCode;

    private Double latitude;

    private Double longitude;

}
