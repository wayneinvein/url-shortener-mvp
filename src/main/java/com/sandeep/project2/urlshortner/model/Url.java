package com.sandeep.project2.urlshortner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "urls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    @Id
    private Long id;

    private String longUrl;

    private String shortCode;

    private LocalDateTime createdAt;
}