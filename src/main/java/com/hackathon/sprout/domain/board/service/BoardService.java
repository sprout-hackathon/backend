package com.hackathon.sprout.domain.board.service;

import com.hackathon.sprout.domain.board.domain.Board;
import com.hackathon.sprout.domain.board.domain.Comment;
import com.hackathon.sprout.domain.board.dto.BoardRequest;
import com.hackathon.sprout.domain.board.dto.BoardResponse;
import com.hackathon.sprout.domain.board.dto.CommentResponse;
import com.hackathon.sprout.domain.board.exception.BoardNotFoundException;
import com.hackathon.sprout.domain.board.repository.BoardRepository;
import com.hackathon.sprout.domain.board.repository.CommentRepository;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.exception.UserNotFoundException;
import com.hackathon.sprout.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createBoard(Authentication authentication, BoardRequest boardRequest) {
        String userId = (String) authentication.getPrincipal();

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Board board = Board.builder()
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent())
                .category(boardRequest.getCategory())
                .views(0)
                .isAnonymous(boardRequest.getIsAnonymous())
                .isDeleted(false)
                .userId(user)
                .build();
        boardRepository.save(board);
    }

    @Transactional
    public void updateBoard(Authentication authentication, Long boardId, BoardRequest boardRequest) {
        String userId = (String) authentication.getPrincipal();

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);

        board.setTitle(boardRequest.getTitle());
        board.setCategory(boardRequest.getCategory());
        board.setContent(boardRequest.getContent());
        board.setIsAnonymous(boardRequest.getIsAnonymous());

        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Authentication authentication, Long boardId) {
        String userId = (String) authentication.getPrincipal();

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);

        board.setIsDeleted(true);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public BoardResponse getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);

        List<Comment> comments = commentRepository.findAllByBoardId(board);
        List<CommentResponse> commentResponses = comments.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());

        return new BoardResponse(board, commentResponses);
    }
}
