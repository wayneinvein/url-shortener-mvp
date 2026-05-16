package com.sandeep.project2.urlshortner.service;

import com.sandeep.project2.urlshortner.model.Url;
import com.sandeep.project2.urlshortner.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String BASE_URL = "http://localhost:8080/"; // will change in prod


    public String createShortUrl(String originalUrl) {

        log.info("request accepted for creating short url of this: {}", originalUrl);

        originalUrl = originalUrl.trim();
        if (originalUrl.startsWith("\"") && originalUrl.endsWith("\"")) {
            originalUrl = originalUrl.substring(1, originalUrl.length() - 1);
        }
        log.info("cleaned original link before conversion");

        // 1. Save URL first to get ID
        Url url = new Url();
        url.setLongUrl(originalUrl);
        Url savedUrl = urlRepository.save(url);

        log.info("long url has been saved in DB");

        // 2. Convert ID → Base62
        String shortCode = encodeBase62(savedUrl.getId());

        log.info("shortcode for long url has been created: {}", shortCode);

        // 3. Update entity with shortCode
        savedUrl.setShortCode(shortCode);
        urlRepository.save(savedUrl);

        log.info("shortcode has been saved in DB");

        // 4. Return full short URL
        return BASE_URL + shortCode;
    }

    // Base62 Encoding Logic
    public String encodeBase62(long id) {

        log.info("base62 encoding for id has started");

        if (id == 0) return "0";

        StringBuilder shortCode = new StringBuilder();

        while (id > 0) {
            int remainder = (int) (id % 62);
            shortCode.append(BASE62.charAt(remainder));
            id /= 62;
        }

        return shortCode.reverse().toString();
    }

    public String getOriginalUrl(String shortCode) {

        log.info("request received for getting original url corresponding to shortcode: {}", shortCode);

        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));
        return url.getLongUrl();
    }
}
