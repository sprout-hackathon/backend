package com.hackathon.sprout.domain.recruitment.repository;

import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    @Query("SELECT r FROM Recruitment r WHERE r.hospital.address.sido = :sido")
    List<Recruitment> findAllBySido(@Param("sido") String sido);

    @Query("SELECT r FROM Recruitment r WHERE r.hospital.address.sido = :sido")
    Page<Recruitment> findAllBySido(@Param("sido") String sido, Pageable pageable);
}