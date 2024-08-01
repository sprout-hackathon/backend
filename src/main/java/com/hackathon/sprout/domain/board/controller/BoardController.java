package com.hackathon.sprout.domain.board.controller;

import com.hackathon.sprout.domain.board.dto.*;
import com.hackathon.sprout.domain.board.service.BoardService;
import com.hackathon.sprout.domain.board.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@Tag(name = "게시판", description = "게시판 관련 API")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @Operation(summary = "게시글 생성", description = "새로운 게시글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Void> createBoard(Authentication authentication, @RequestBody BoardRequest boardRequest) {
        boardService.createBoard(authentication, boardRequest);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "게시글 수정", description = "기존 게시글을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{boardId}")
    public ResponseEntity<Void> updateBoard(Authentication authentication, @PathVariable("boardId") Long boardId, @RequestBody BoardRequest boardRequest) {
        boardService.updateBoard(authentication, boardId, boardRequest);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "게시글 삭제", description = "기존 게시글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "게시글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(Authentication authentication, @PathVariable("boardId") Long boardId) {
        boardService.deleteBoard(authentication, boardId);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "댓글 생성", description = "새로운 댓글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "댓글 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "게시글 또는 사용자를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/comments")
    public ResponseEntity<Void> createComment(Authentication authentication, @RequestBody CommentRequest commentRequest) {
        commentService.createComment(authentication, commentRequest);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "댓글 수정", description = "기존 댓글을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "댓글을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(Authentication authentication, @PathVariable("commentId") Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        commentService.updateComment(authentication, commentId, commentUpdateRequest);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "댓글 삭제", description = "기존 댓글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "댓글을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(Authentication authentication, @PathVariable("commentId") Long commentId) {
        commentService.deleteComment(authentication, commentId);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "게시글 조회", description = "특정 게시글의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = @Content(schema = @Schema(implementation = BoardDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDetailResponse> getBoard(@PathVariable("boardId") Long boardId) {
        BoardDetailResponse boardDetailResponse = boardService.getBoard(boardId);
        return ResponseEntity.status(200).body(boardDetailResponse);
    }

    @Operation(summary = "게시글 목록 조회", description = "모든 게시글의 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공", content = @Content(schema = @Schema(implementation = BoardResponse.class)))
    })
    @GetMapping
    public ResponseEntity<Page<BoardResponse>> getAllBoards(@PageableDefault(size = 10) Pageable pageable) {
        Page<BoardResponse> boardPage = boardService.getAllBoards(pageable);
        return ResponseEntity.status(200).body(boardPage);
    }
}
