package com.sandeep.project2.urlshortner.repository;

import com.sandeep.project2.urlshortner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortCode(String shortCode);
}
