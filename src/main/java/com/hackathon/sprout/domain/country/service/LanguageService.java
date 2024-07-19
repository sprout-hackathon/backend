package com.hackathon.sprout.domain.country.service;

import com.hackathon.sprout.domain.country.domain.Language;
import com.hackathon.sprout.domain.country.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}
