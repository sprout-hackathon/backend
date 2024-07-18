package com.hackathon.sprout.domain.board.domain;

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
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long boardId;

    @Column(length = 100)
    private String title;

    @Column(length = 3000)
    private String content;

    @Column
    private String category;

    @Column
    private Integer views;

    @Column
    private Boolean isAnonymous;

    @Column
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}