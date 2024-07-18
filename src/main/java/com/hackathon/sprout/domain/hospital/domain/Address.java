package com.hackathon.sprout.domain.hospital.domain;

import com.hackathon.sprout.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer addressId;

    @Column(length = 20, nullable = false)
    private String sido;

    @Column(length = 20)
    private String gunGu;

    @Column(length = 100)
    private String roadName;

    @Column(length = 20)
    private String buildingNumber;

    @Column(length = 200)
    private String detailAddress;
}
