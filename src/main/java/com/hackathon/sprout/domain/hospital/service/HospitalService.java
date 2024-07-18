package com.hackathon.sprout.domain.hospital.service;

import com.hackathon.sprout.domain.hospital.domain.Hospital;
import com.hackathon.sprout.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public List<Hospital> getHospitalList(String name){
        return hospitalRepository.findAllByNameContaining(name);
    }

    public Hospital getHospital(Long hospitalId){
        return hospitalRepository.findById(hospitalId).orElseThrow();
    }
}
