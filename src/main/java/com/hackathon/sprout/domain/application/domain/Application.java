package com.hackathon.sprout.domain.application.domain;

import com.hackathon.sprout.domain.application.enums.ApplicationState;
import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long applicationId;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ApplicationState state = ApplicationState.RECEIVED;

    @ManyToOne
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //== 비즈니스 로직 ==//
    public void updateState(ApplicationState state){
        this.state = state;
    }
}