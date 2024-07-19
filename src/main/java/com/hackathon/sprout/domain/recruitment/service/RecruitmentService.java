package com.hackathon.sprout.domain.recruitment.service;

import com.hackathon.sprout.domain.hospital.service.HospitalService;
import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentCreateRequest;
import com.hackathon.sprout.domain.recruitment.dto.SearchCondition;
import com.hackathon.sprout.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final HospitalService hospitalService;

    public Long createRecruitment(RecruitmentCreateRequest request){
        return recruitmentRepository.save(request.toEntity(hospitalService.getHospital(request.getHospitalId())))
            .getRecruitmentId();
    }

    public Page<Recruitment> getRecruitmentList(SearchCondition condition, Pageable pageable){
        return recruitmentRepository.findAllBySido(condition.getSido(), pageable);
    }

    public List<Recruitment> getRecruitmentList(){
        return recruitmentRepository.findAll();
    }

    public Recruitment getRecruitment(Long recruitmentId) {
        return recruitmentRepository.findById(recruitmentId).orElseThrow();
    }
}
