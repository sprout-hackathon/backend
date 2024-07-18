package com.hackathon.sprout.domain.recruitment.domain;

import com.hackathon.sprout.domain.hospital.domain.Hospital;
import com.hackathon.sprout.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long recruitmentId;

    @Column
    private Byte requiredExp;

    @Column
    private Byte conditionsDay;

    @Column
    private Byte conditionsHour;

    @Column(length = 3000)
    private String content;

    @Column(nullable = false)
    private Integer salary;

    @Column(length = 10)
    private String state;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospitalId;
}