package com.hackathon.sprout.domain.chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatSearchCondition {
    @Schema(description = "검색 조건 날짜", example = "2024-01-01")
    String date;
}
