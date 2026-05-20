package com.sandeep.project2.urlshortner.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlRequestDTO {

        @NotBlank(message = "URL cannot be empty")
        @Pattern(
                regexp = "^(http|https)://.*$",
                message = "URL must start with http:// or https://"
        )
        private String longUrl;
    }