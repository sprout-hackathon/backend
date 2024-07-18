package com.hackathon.sprout.domain.country.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Language {
    @Id
    @Column(length = 5)
    String languageCode;

    @Column(length = 50)
    String languageOriginName;

    @Column(length = 50)
    String languageName;
}
