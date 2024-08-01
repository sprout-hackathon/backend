package com.hackathon.sprout.domain.recruitment.service;

import com.hackathon.sprout.domain.hospital.service.HospitalService;
import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import com.hackathon.sprout.domain.recruitment.domain.RecruitmentScrap;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentCreateRequest;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentSearchCondition;
import com.hackathon.sprout.domain.recruitment.repository.RecruitmentRepository;
import com.hackathon.sprout.domain.recruitment.repository.RecruitmentScrapRepository;
import com.hackathon.sprout.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentScrapRepository recruitmentScrapRepository;
    private final HospitalService hospitalService;
    private final UserService userService;

    public Long createRecruitment(RecruitmentCreateRequest request){
        return recruitmentRepository.save(request.toEntity(hospitalService.getHospital(request.getHospitalId())))
            .getRecruitmentId();
    }

    public Page<Recruitment> getRecruitmentList(RecruitmentSearchCondition condition, Pageable pageable){
        if(condition.getSido().isEmpty()){
            return recruitmentRepository.findAll(pageable);
        }else{
            return recruitmentRepository.findAllBySido(condition.getSido(), pageable);
        }
    }

    public List<Recruitment> getRecruitmentList(){
        return recruitmentRepository.findAll();
    }

    public Recruitment getRecruitment(Long recruitmentId) {
        return recruitmentRepository.findById(recruitmentId).orElseThrow();
    }
    public Long checkScrap(Long userId, Long recruitmentId) {
        return recruitmentScrapRepository.countByUserIdAndRecruitmentId(userId,recruitmentId);
    }

    public Long scrapRecruitment(Long recruitmentId){
        return recruitmentScrapRepository.save(RecruitmentScrap.builder()
                .recruitment(getRecruitment(recruitmentId))
                .user(userService.getUserFromAuth())
                .build())
                .getRecruitmentScrapId();
    }

    public void cancelScrap(Long userId, Long recruitmentId){
        recruitmentScrapRepository.deleteByUserIdAndRecruitmentId(userId,recruitmentId);
    }

    public List<RecruitmentScrap> getScrapList(){
        return recruitmentScrapRepository.findAllByUser(userService.getUserFromAuth());
    }
}
