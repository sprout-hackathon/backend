package com.hackathon.sprout.domain.recruitment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecruitmentSearchCondition {
    @Schema(description = "시도", example = "서울")
    String sido = "";
}
