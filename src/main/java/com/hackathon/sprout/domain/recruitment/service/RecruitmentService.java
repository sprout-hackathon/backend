package com.hackathon.sprout.domain.recruitment.service;

import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentCreateRequest;
import com.hackathon.sprout.domain.recruitment.dto.SearchCondition;
import com.hackathon.sprout.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;

    public Page<Recruitment> getRecruitmentList(SearchCondition condition, Pageable pageable){
        return recruitmentRepository.findAllBySido(condition.getSido(), pageable);
    }

    public Long createRecruitment(RecruitmentCreateRequest request){
        return recruitmentRepository.save(Recruitment.builder()
                .build())
                .getRecruitmentId();
    }
}
