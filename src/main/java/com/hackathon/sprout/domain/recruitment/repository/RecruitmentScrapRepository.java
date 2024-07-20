package com.hackathon.sprout.domain.recruitment.repository;

import com.hackathon.sprout.domain.recruitment.domain.RecruitmentScrap;
import com.hackathon.sprout.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentScrapRepository extends JpaRepository<RecruitmentScrap, Long> {
    List<RecruitmentScrap> findAllByUser(User user);
}