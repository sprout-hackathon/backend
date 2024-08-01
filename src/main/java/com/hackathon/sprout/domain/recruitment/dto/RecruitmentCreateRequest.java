package com.hackathon.sprout.domain.recruitment.dto;

import com.hackathon.sprout.domain.hospital.domain.Hospital;
import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RecruitmentCreateRequest {
    @Schema(description = "필요 경력", example = "3")
    private Byte requiredExp;

    @Schema(description = "근무 조건 - 일", example = "5")
    private Byte conditionsDay;

    @Schema(description = "근무 조건 - 시간", example = "40")
    private Byte conditionsHour;

    @Schema(description = "채용 공고 제목", example = "간호사 모집")
    private String title;

    @Schema(description = "채용 공고 내용", example = "서울대학교병원에서 간호사를 모집합니다.")
    private String content;

    @Schema(description = "연봉", example = "5000")
    private Integer salary;

    @Schema(description = "채용 상태", example = "진행중")
    private String state;

    @Schema(description = "병원 ID", example = "1")
    private Long hospitalId;

    public Recruitment toEntity(Hospital hospital) {
        return Recruitment.builder()
                .requiredExp(requiredExp)
                .conditionsDay(conditionsDay)
                .conditionsHour(conditionsHour)
                .title(title)
                .content(content)
                .salary(salary)
                .state(state)
                .hospital(hospital)
                .build();
    }
}
