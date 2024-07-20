package com.hackathon.sprout.domain.workhistory.domain;

import com.hackathon.sprout.domain.hospital.domain.Hospital;
import com.hackathon.sprout.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long workHistoryId;

    @Column(nullable = false)
    private Byte workDuration;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
