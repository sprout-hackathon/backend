package com.hackathon.sprout.domain.country.repository;

import com.hackathon.sprout.domain.country.domain.Nation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationRepository extends JpaRepository<Nation, String> {
}
