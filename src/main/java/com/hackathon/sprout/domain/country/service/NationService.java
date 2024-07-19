package com.hackathon.sprout.domain.country.service;

import com.hackathon.sprout.domain.country.domain.Nation;
import com.hackathon.sprout.domain.country.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationService {
    @Autowired
    private NationRepository nationRepository;

    public List<Nation> getAllNations() {
        return nationRepository.findAll();
    }
}
