package com.sandeep.project2.urlshortner.repository;

import com.sandeep.project2.urlshortner.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<Url, String> {

    Optional<Url> findByShortCode(String shortCode);
}
