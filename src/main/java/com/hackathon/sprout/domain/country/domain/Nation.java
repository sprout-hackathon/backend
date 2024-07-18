package com.hackathon.sprout.domain.country.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nation {
    @Id
    @Column(length = 5)
    String nationCode;

    @Column(length = 100)
    String nationOriginName;

    @Column(length = 100)
    String nationName;
}
