package com.hackathon.sprout.domain.recruitment.dto;


import com.hackathon.sprout.domain.recruitment.domain.RecruitmentScrap;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class RecruitmentScrapResponse {
    private final Long recruitmentScrapId;

    private final RecruitmentResponse recruitment;

    public RecruitmentScrapResponse(RecruitmentScrap recruitmentScrap) {
        this.recruitmentScrapId = recruitmentScrap.getRecruitmentScrapId();
        this.recruitment = new RecruitmentResponse(recruitmentScrap.getRecruitment());
    }
}
