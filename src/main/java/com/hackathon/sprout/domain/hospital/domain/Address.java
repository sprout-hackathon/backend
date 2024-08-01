package com.hackathon.sprout.domain.hospital.domain;

import com.hackathon.sprout.global.shared.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "주소 ID", example = "1")
    @Column
    private Integer addressId;

    @Schema(description = "시/도", example = "서울")
    @Column(length = 20, nullable = false)
    private String sido;

    @Schema(description = "군/구", example = "광진구")
    @Column(length = 20)
    private String gunGu;

    @Schema(description = "도로명", example = "동일로")
    @Column(length = 100)
    private String roadName;

    @Schema(description = "건물 번호", example = "1")
    @Column(length = 20)
    private String buildingNumber;

    @Schema(description = "상세 주소", example = "서울대학교병원 본관 1층")
    @Column(length = 200)
    private String detailAddress;
}
