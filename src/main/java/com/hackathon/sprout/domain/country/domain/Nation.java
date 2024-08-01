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
public class Nation {
    @Id
    @Schema(description = "국가 코드", example = "CA")
    @Column(length = 5)
    String nationCode;

    @Schema(description = "국가 원어 이름", example = "Canada")
    @Column(length = 100)
    String nationOriginName;

    @Schema(description = "국가 이름", example = "'캐나다'")
    @Column(length = 100)
    String nationName;
}