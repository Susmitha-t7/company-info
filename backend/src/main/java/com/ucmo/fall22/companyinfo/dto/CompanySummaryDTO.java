package com.ucmo.fall22.companyinfo.dto;

import lombok.Data;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class CompanySummaryDTO {

    private CompanyDTO companyDTO;
    private String description;
    private String overview;
    private String crunchbaseUrl;
    private String homepageUrl;
    private String blogUrl;
    private String blogFeedUrl;
    private String twitterUsername;
    //URLS
    //Address List<Offices>
}
