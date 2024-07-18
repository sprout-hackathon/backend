package com.hackathon.sprout.domain.hospital.repository;

import com.hackathon.sprout.domain.hospital.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findAllByNameContaining(String name);
}
