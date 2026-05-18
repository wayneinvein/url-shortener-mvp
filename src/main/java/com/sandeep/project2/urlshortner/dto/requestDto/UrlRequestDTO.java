package com.sandeep.project2.urlshortner.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UrlRequestDTO {

        @NotBlank(message = "URL cannot be empty")
        @Pattern(
                regexp = "^(http|https)://.*$",
                message = "URL must start with http:// or https://"
        )
        private String longUrl;
    }