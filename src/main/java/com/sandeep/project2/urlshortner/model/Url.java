package com.sandeep.project2.urlshortner.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
public class Url {

    @Id
    private String id;

    private String longUrl;

    private String shortCode;

    private LocalDateTime createdAt;
}