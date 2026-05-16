package com.sandeep.project2.urlshortner.controller;

import com.sandeep.project2.urlshortner.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    // Create short URL
    @PostMapping("/shorten")
    public ResponseEntity<String> createShortUrl(@RequestBody String originalUrl) {
        String shortUrl = urlService.createShortUrl(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }

    // Redirect API
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        String originalUrl = urlService.getOriginalUrl(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND) // 302
                .header(HttpHeaders.LOCATION, originalUrl)
                .build();
    }

}
