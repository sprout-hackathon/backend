package com.hackathon.sprout.domain.chat.domain;

import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageRoomId;

    @Column
    private String title;

//    @Column(length = 3000)
//    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_room_id")
    @Builder.Default
    List<ImageMessage> imageMessageList = new ArrayList<>();
}
