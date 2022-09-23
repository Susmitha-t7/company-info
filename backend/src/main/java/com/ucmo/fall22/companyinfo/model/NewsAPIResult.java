package com.ucmo.fall22.companyinfo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NewsAPIResult {

    @JsonProperty("status")
    String status;

    @JsonProperty("articles")
    List<News> articles;
}
