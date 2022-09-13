package com.ucmo.fall22.companyinfo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Relationship {

    @Field(value = "is_past")
    private boolean isPast;

    @Field(value = "title")
    private String title;

    @Field(value = "person")
    private Person person;
}
