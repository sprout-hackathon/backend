package com.hackathon.sprout.domain.recruitment.dto;

import com.hackathon.sprout.domain.recruitment.domain.RecruitmentScrap;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class RecruitmentScrapResponse {
    @Schema(description = "스크랩 ID", example = "1")
    private final Long recruitmentScrapId;

    @Schema(description = "채용 공고 정보")
    private final RecruitmentResponse recruitment;

    public RecruitmentScrapResponse(RecruitmentScrap recruitmentScrap) {
        this.recruitmentScrapId = recruitmentScrap.getRecruitmentScrapId();
        this.recruitment = new RecruitmentResponse(recruitmentScrap.getRecruitment());
    }
}
