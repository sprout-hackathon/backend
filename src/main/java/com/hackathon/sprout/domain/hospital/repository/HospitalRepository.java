package com.hackathon.sprout.domain.hospital.repository;

import com.hackathon.sprout.domain.hospital.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
