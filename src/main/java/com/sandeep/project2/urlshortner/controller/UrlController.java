package com.sandeep.project2.urlshortner.controller;

import com.sandeep.project2.urlshortner.dto.requestDto.UrlRequestDTO;
import com.sandeep.project2.urlshortner.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    // Create short URL
    @PostMapping("/shorten")
    public ResponseEntity<String> createShortUrl(@Valid @RequestBody UrlRequestDTO originalUrl) {

        log.info("conversion request received at controller for url: {}", originalUrl);
        String shortUrl = urlService.createShortUrl(originalUrl);
        log.info("completed long url -> short url conversion now returning back to client");
        return ResponseEntity.ok(shortUrl);
    }

    // Redirect API
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        log.info("redirect request received at controller for shortcode: {}", shortCode);

        String originalUrl = urlService.getOriginalUrl(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND) // 302
                .header(HttpHeaders.LOCATION, originalUrl)
                .build();
    }

}
