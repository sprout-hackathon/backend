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
public class Hospital extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "병원 ID", example = "1")
    @Column
    private Long hospitalId;

    @Schema(description = "병원 이름", example = "서울대학교병원")
    @Column(length = 30)
    private String name;

    @Schema(description = "병원 전화번호", example = "02-1234-5678")
    @Column(length = 15)
    private String phoneNumber;

    @Schema(description = "병원 웹사이트 URL", example = "https://www.www.www")
    @Column(length = 2100)
    private String webUrl;

    @Schema(description = "병원 설명", example = "서울대학교병원은 대한민국 서울특별시에 위치한 병원입니다.")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Schema(description = "병원 주소")
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
