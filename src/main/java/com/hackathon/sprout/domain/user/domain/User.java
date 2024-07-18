package com.hackathon.sprout.domain.user.domain;

import com.hackathon.sprout.domain.country.domain.Language;
import com.hackathon.sprout.domain.country.domain.Nation;
import com.hackathon.sprout.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;

    @Column(length = 30, nullable = false, unique = true)
    private String id;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "nation_code")
    private Nation nationCode;

    @ManyToOne
    @JoinColumn(name = "language_code")
    private Language languageCode;

    @Column
    private Byte proficiency;

    @Column
    private Boolean hasCertification;

    @Column(length = 100)
    private String certificationCode;

    @Column(length = 255)
    private String refreshToken;

    @Column
    private Boolean isDeleted;
}
