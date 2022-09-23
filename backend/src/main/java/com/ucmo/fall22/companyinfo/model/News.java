package com.ucmo.fall22.companyinfo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class News {

    @JsonProperty("source.name")
    String sourceName;

    @JsonProperty("author")
    String author;

    @JsonProperty("title")
    String title;

    @JsonProperty("url")
    String url;

    @JsonProperty("urlToImage")
    String urlToImage;
}
