package com.hackathon.sprout.domain.recruitment.dto;

import com.hackathon.sprout.domain.hospital.dto.HospitalResponse;
import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class RecruitmentResponse {
    @Schema(description = "채용 공고 ID", example = "1")
    private final Long recruitmentId;

    @Schema(description = "필요 경력", example = "3")
    private final Byte requiredExp;

    @Schema(description = "근무 조건 - 일", example = "5")
    private final Byte conditionsDay;

    @Schema(description = "근무 조건 - 시간", example = "40")
    private final Byte conditionsHour;

    @Schema(description = "채용 공고 제목", example = "간호사 모집")
    private final String title;

    @Schema(description = "채용 공고 내용", example = "서울대학교병원에서 간호사를 모집합니다.")
    private final String content;

    @Schema(description = "연봉", example = "5000")
    private final Integer salary;

    @Schema(description = "채용 상태", example = "open")
    private final String state;

    @Schema(description = "병원 정보")
    private final HospitalResponse hospital;

    public RecruitmentResponse(Recruitment recruitment) {
        this.recruitmentId = recruitment.getRecruitmentId();
        this.requiredExp = recruitment.getRequiredExp();
        this.conditionsDay = recruitment.getConditionsDay();
        this.conditionsHour = recruitment.getConditionsHour();
        this.title = recruitment.getTitle();
        this.content = recruitment.getContent();
        this.salary = recruitment.getSalary();
        this.state = recruitment.getState();
        this.hospital = new HospitalResponse(recruitment.getHospital());
    }
}
