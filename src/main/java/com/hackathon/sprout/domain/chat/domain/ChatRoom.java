package com.hackathon.sprout.domain.chat.domain;

import com.hackathon.sprout.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long chatRoomId;

    @Column
    private String title;

    @Column(length = 3000)
    private String content;

    @Column
    private Long userId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "chat_room_id")
    @Builder.Default
    List<ChatMessage> chatMessageList = new ArrayList<>();
}
