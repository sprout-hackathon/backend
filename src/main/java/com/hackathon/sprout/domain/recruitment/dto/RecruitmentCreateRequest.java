package com.hackathon.sprout.domain.recruitment.dto;


import com.hackathon.sprout.domain.hospital.domain.Hospital;
import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RecruitmentCreateRequest {
    private Byte requiredExp;

    private Byte conditionsDay;

    private Byte conditionsHour;

    private String title;

    private String content;

    private Integer salary;

    private String state;

    // foreign key
    private Long hospitalId;

    public Recruitment toEntity(Hospital hospital){
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
