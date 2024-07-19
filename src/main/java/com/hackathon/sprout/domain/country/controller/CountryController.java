package com.hackathon.sprout.domain.country.controller;

import com.hackathon.sprout.domain.country.domain.Language;
import com.hackathon.sprout.domain.country.domain.Nation;
import com.hackathon.sprout.domain.country.service.LanguageService;
import com.hackathon.sprout.domain.country.service.NationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nations")
@RequiredArgsConstructor
public class CountryController {
    private final LanguageService languageService;
    private final NationService nationService;

    @GetMapping
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    @GetMapping("/languages")
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }
}