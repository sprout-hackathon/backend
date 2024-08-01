package com.hackathon.sprout.domain.recruitment.controller;

import com.hackathon.sprout.domain.recruitment.dto.RecruitmentCreateRequest;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentResponse;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentScrapResponse;
import com.hackathon.sprout.domain.recruitment.dto.SearchCondition;
import com.hackathon.sprout.domain.recruitment.service.RecruitmentService;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.service.UserService;
import com.hackathon.sprout.global.error.ErrorResponse;
import com.hackathon.sprout.global.shared.AuthUtil;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruitments")
@Tag(name = "채용 공고", description = "채용 공고 관련 API")
@RequiredArgsConstructor
public class RecruitmentController {
    private final RecruitmentService recruitmentService;
    private final UserService userService;

    @Operation(summary = "채용 공고 생성", description = "새로운 채용 공고를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "채용 공고 생성 성공", content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping
    public ResponseEntity<Long> createRecruitment(@RequestBody RecruitmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recruitmentService.createRecruitment(request));
    }

    @Operation(summary = "채용 공고 검색", description = "조건에 맞는 채용 공고를 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채용 공고 검색 성공", content = @Content(schema = @Schema(implementation = RecruitmentResponse.class))),
    })
    @GetMapping
    public ResponseEntity<Page<RecruitmentResponse>> searchRecruitment(
            @ModelAttribute final SearchCondition condition,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(recruitmentService.getRecruitmentList(condition, pageable).map(RecruitmentResponse::new));
    }

    @Operation(summary = "채용 공고 상세 조회", description = "채용 공고 ID를 사용하여 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채용 공고 조회 성공", content = @Content(schema = @Schema(implementation = RecruitmentResponse.class))),
            @ApiResponse(responseCode = "404", description = "채용 공고를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{recruitmentId}")
    public ResponseEntity<RecruitmentResponse> getRecruitment(@PathVariable Long recruitmentId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new RecruitmentResponse(recruitmentService.getRecruitment(recruitmentId)));
    }

    @Operation(summary = "스크랩 여부 확인", description = "사용자가 특정 채용 공고를 스크랩했는지 확인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스크랩 여부 확인 성공", content = @Content(schema = @Schema(implementation = Boolean.class))),
    })
    @GetMapping("/{recruitmentId}/scrap")
    public ResponseEntity<Boolean> isScrap(Authentication authentication, @PathVariable Long recruitmentId) {
        String userId = AuthUtil.getUserIdFromAuth(authentication);
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(recruitmentService.checkScrap(user.getUserId(), recruitmentId) == 1);
    }

    @Operation(summary = "채용 공고 스크랩", description = "채용 공고를 스크랩합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "스크랩 성공", content = @Content(schema = @Schema(implementation = Long.class))),
    })
    @PostMapping("/{recruitmentId}/scrap")
    public ResponseEntity<Long> scrapRecruitment(@PathVariable Long recruitmentId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recruitmentService.scrapRecruitment(recruitmentId));
    }

    @Operation(summary = "스크랩 취소", description = "채용 공고 스크랩을 취소합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "스크랩 취소 성공"),
    })
    @DeleteMapping("/{recruitmentId}/scrap")
    public ResponseEntity<Void> cancelScrap(Authentication authentication, @PathVariable Long recruitmentId) {
        String userId = AuthUtil.getUserIdFromAuth(authentication);
        User user = userService.getUser(userId);
        recruitmentService.cancelScrap(user.getUserId(), recruitmentId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "스크랩 목록 조회", description = "사용자가 스크랩한 채용 공고 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스크랩 목록 조회 성공", content = @Content(schema = @Schema(implementation = RecruitmentScrapResponse.class))),
    })
    @GetMapping("/scrap")
    public ResponseEntity<List<RecruitmentScrapResponse>> getScrapList() {
        return ResponseEntity.ok(recruitmentService.getScrapList().stream().map(RecruitmentScrapResponse::new).toList());
    }
}
