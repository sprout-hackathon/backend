package com.hackathon.sprout.domain.country.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Language {
    @Id
    @Schema(description = "언어 코드", example = "en")
    @Column(length = 5)
    String languageCode;

    @Schema(description = "언어 원어 이름", example = "English")
    @Column(length = 50)
    String languageOriginName;

    @Schema(description = "언어 이름", example = "영어")
    @Column(length = 50)
    String languageName;
}