package com.sandeep.project2.urlshortner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //it'll make sure the input is in correct form
    @NotBlank
    @Pattern(
            regexp = "^(http://|https://).+",
            message = "URL must start with http:// or https://"
    )
    private String longUrl;

    private String shortCode;
}