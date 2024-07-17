package com.hackathon.sprout.domain.chat.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long chatRoomId;

    @Column
    private String title;

    @Column(length = 3000)
    private String content;

    @Column
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private Long userId;
}
