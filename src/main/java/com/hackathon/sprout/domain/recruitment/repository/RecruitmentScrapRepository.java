package com.hackathon.sprout.domain.recruitment.repository;

import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import com.hackathon.sprout.domain.recruitment.domain.RecruitmentScrap;
import com.hackathon.sprout.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentScrapRepository extends JpaRepository<RecruitmentScrap, Long> {
    List<RecruitmentScrap> findAllByUser(User user);

    @Modifying
    @Query("delete from RecruitmentScrap rs where rs.user.userId = :userId and rs.recruitment.recruitmentId = :recruitmentId")
    void deleteByUserIdAndRecruitmentId(@Param("userId") Long userId, @Param("recruitmentId") Long recruitmentId);

    @Query("select count(*) from RecruitmentScrap rs where rs.user.userId = :userId and rs.recruitment.recruitmentId = :recruitmentId")
    Long countByUserIdAndRecruitmentId(@Param("userId") Long userId, @Param("recruitmentId") Long recruitmentId);

    @Modifying
    void deleteByUserAndRecruitment(User user, Recruitment recruitment);
}