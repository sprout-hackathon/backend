package com.hackathon.sprout.domain.recruitment.dto;


import com.hackathon.sprout.domain.hospital.dto.HospitalResponse;
import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import lombok.Getter;


@Getter
public class RecruitmentResponse {
    private final Long recruitmentId;

    private final Byte requiredExp;

    private final Byte conditionsDay;

    private final Byte conditionsHour;

    private final String content;

    private final Integer salary;

    private final String state;

    private final HospitalResponse hospital;

    public RecruitmentResponse(Recruitment recruitment) {
        this.recruitmentId = recruitment.getRecruitmentId();
        this.requiredExp = recruitment.getRequiredExp();
        this.conditionsDay = recruitment.getConditionsDay();
        this.conditionsHour = recruitment.getConditionsHour();
        this.content = recruitment.getContent();
        this.salary = recruitment.getSalary();
        this.state = recruitment.getState();
        this.hospital = new HospitalResponse(recruitment.getHospital());
    }
}
