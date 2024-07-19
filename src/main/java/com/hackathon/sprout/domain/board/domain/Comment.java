package com.hackathon.sprout.domain.board.domain;

import com.hackathon.sprout.domain.board.domain.Board;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.global.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long commentId;

    @Column(length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board boardId;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentCommentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}
