package com.ucmo.fall22.companyinfo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class CompanyDepend {

    private String name;

    private String permalink;
}
