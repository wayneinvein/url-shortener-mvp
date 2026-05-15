package com.sandeep.project2.urlshortner.repository;

import com.sandeep.project2.urlshortner.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {
}
