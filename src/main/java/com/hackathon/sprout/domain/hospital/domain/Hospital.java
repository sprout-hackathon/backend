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
public class Hospital extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long hospitalId;

    @Column(length = 30)
    private String name;

    @Column(length = 15)
    private String phoneNumber;

    @Column(length = 2100)
    private String webUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
