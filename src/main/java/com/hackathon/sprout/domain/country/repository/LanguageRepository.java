package com.hackathon.sprout.domain.country.repository;

import com.hackathon.sprout.domain.country.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, String> {
}
