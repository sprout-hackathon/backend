package com.hackathon.sprout.domain.board.service;

import com.hackathon.sprout.domain.board.domain.Board;
import com.hackathon.sprout.domain.board.domain.Comment;
import com.hackathon.sprout.domain.board.dto.CommentRequest;
import com.hackathon.sprout.domain.board.dto.CommentUpdateRequest;
import com.hackathon.sprout.domain.board.exception.BoardNotFoundException;
import com.hackathon.sprout.domain.board.exception.CommentNotFoundException;
import com.hackathon.sprout.domain.board.repository.BoardRepository;
import com.hackathon.sprout.domain.board.repository.CommentRepository;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.exception.UserNotFoundException;
import com.hackathon.sprout.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createComment(Authentication authentication, CommentRequest commentRequest) {
        String userId = (String) authentication.getPrincipal();

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Board board = boardRepository.findById(commentRequest.getBoardId())
                .orElseThrow(BoardNotFoundException::new);

        Comment parentComment = null;
        if (commentRequest.getParentCommentId() != null) {
            parentComment = commentRepository.findById(commentRequest.getParentCommentId())
                    .orElseThrow(CommentNotFoundException::new);
        }

        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .parentCommentId(parentComment)
                .boardId(board)
                .userId(user)
                .build();
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Authentication authentication, Long commentId, CommentUpdateRequest commentUpdateRequest) {
        String userId = (String) authentication.getPrincipal();

        userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        comment.setContent(commentUpdateRequest.getContent());

        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Authentication authentication, Long commentId) {
        String userId = (String) authentication.getPrincipal();

        userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        commentRepository.delete(comment);
    }
}
