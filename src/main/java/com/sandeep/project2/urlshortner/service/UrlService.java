package com.sandeep.project2.urlshortner.service;

import com.sandeep.project2.urlshortner.model.Url;
import com.sandeep.project2.urlshortner.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String BASE_URL = "http://localhost:8080/"; // will change in prod


    public String createShortUrl(String originalUrl) {

        // 1. Save URL first to get ID
        Url url = new Url();
        url.setLongUrl(originalUrl);

        Url savedUrl = urlRepository.save(url);
        System.out.println(savedUrl + ": saved url");

        // 2. Convert ID → Base62
        String shortCode = encodeBase62(savedUrl.getId());

        // 3. Update entity with shortCode
        savedUrl.setShortCode(shortCode);
        urlRepository.save(savedUrl);

        // 4. Return full short URL
        return BASE_URL + shortCode;
    }

    // Base62 Encoding Logic
    private String encodeBase62(Long num) {
        if (num == 0) return "0";

        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int remainder = (int) (num % 62);
            sb.append(BASE62.charAt(remainder));
            num /= 62;
        }

        return sb.reverse().toString();
    }
}
