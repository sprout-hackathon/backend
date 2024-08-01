package com.hackathon.sprout.domain.image.domain;

import com.hackathon.sprout.domain.file.domain.File;
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
public class ImageMessage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long imageMessageId;

    @Column(length = 3000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_room_id")
    private ImageRoom imageRoom;

    @Column
    private Boolean isBot;

    @OneToMany(mappedBy = "imageMessage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files = new ArrayList<>();
}