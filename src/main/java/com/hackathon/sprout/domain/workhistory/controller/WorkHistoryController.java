package com.hackathon.sprout.domain.workhistory.controller;

import com.hackathon.sprout.domain.user.service.UserService;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryRequest;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryResponse;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryUpdateRequest;
import com.hackathon.sprout.domain.workhistory.service.WorkHistoryService;
import com.hackathon.sprout.global.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-histories")
@Tag(name = "근무 이력", description = "근무 이력 관련 API")
@RequiredArgsConstructor
public class WorkHistoryController {
    private final WorkHistoryService workHistoryService;
    private final UserService userService;

    @Operation(summary = "근무 이력 생성", description = "새로운 근무 이력을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "근무 이력 생성 성공", content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping
    public ResponseEntity<Long> createWorkHistory(@RequestBody WorkHistoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(workHistoryService.createWorkHistory(userService.getUserFromAuth(), request));
    }

    @Operation(summary = "근무 이력 목록 조회", description = "사용자의 근무 이력 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "근무 이력 목록 조회 성공", content = @Content(schema = @Schema(implementation = WorkHistoryResponse.class))),
    })
    @GetMapping
    public ResponseEntity<List<WorkHistoryResponse>> getWorkHistoryList() {
        return ResponseEntity.ok(workHistoryService.getWorkHistoryList(userService.getUserFromAuth()).stream().map(WorkHistoryResponse::new).toList());
    }

    @Operation(summary = "근무 이력 상세 조회", description = "근무 이력 ID를 사용하여 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "근무 이력 조회 성공", content = @Content(schema = @Schema(implementation = WorkHistoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "근무 이력을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{workHistoryId}")
    public ResponseEntity<WorkHistoryResponse> getWorkHistory(@PathVariable Long workHistoryId) {
        return ResponseEntity.ok(new WorkHistoryResponse(workHistoryService.getWorkHistory(workHistoryId)));
    }

    @Operation(summary = "근무 이력 수정", description = "기존 근무 이력을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "근무 이력 수정 성공", content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "404", description = "근무 이력을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PatchMapping("/{workHistoryId}")
    public ResponseEntity<Long> updateWorkHistory(@PathVariable Long workHistoryId, @RequestBody WorkHistoryUpdateRequest request) {
        workHistoryService.update(workHistoryId, request);
        return ResponseEntity.ok(workHistoryId);
    }

    @Operation(summary = "근무 이력 삭제", description = "기존 근무 이력을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "근무 이력 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "근무 이력을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @DeleteMapping("/{workHistoryId}")
    public ResponseEntity<Void> deleteWorkHistory(@PathVariable Long workHistoryId) {
        workHistoryService.deleteWorkHistory(workHistoryId);
        return ResponseEntity.noContent().build();
    }
}
