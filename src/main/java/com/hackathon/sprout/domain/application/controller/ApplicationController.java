package com.hackathon.sprout.domain.application.controller;

import com.hackathon.sprout.domain.application.dto.ApplicationCreateRequest;
import com.hackathon.sprout.domain.application.dto.ApplicationResponse;
import com.hackathon.sprout.domain.application.dto.ApplicationStateResponse;
import com.hackathon.sprout.domain.application.dto.ApplicationUpdateRequest;
import com.hackathon.sprout.domain.application.enums.ApplicationState;
import com.hackathon.sprout.domain.application.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
@Tag(name = "지원 내역", description = "지원 내역 관련 API")
public class ApplicationController {
    private final ApplicationService applicationService;

    @Operation(summary = "지원 내역 등록", description = "새로운 지원 내역을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "지원 내역 등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Long> registerApplication(@RequestBody ApplicationCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.registerApplication(request));
    }

    @Operation(summary = "지원 내역 가져오기", description = "사용자의 지원 내역을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "지원 내역 가져오기 성공"),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getApplicationList() {
        return ResponseEntity.ok(applicationService.getApplicationList().stream().map(ApplicationResponse::new).toList());
    }

    @Operation(summary = "지원 내역 상태 목록 가져오기", description = "지원 내역 상태 목록을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "지원 내역 상태 목록 가져오기 성공")
    })
    @GetMapping("/states")
    public ResponseEntity<List<ApplicationStateResponse>> getApplicationStateList() {
        return ResponseEntity.ok(Arrays.stream(ApplicationState.values()).map(ApplicationStateResponse::new).toList());
    }

    @Operation(summary = "지원 내역 수정하기", description = "기존 지원 내역을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "지원 내역 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "리소스를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{applicationId}")
    public ResponseEntity<Long> updateApplication(@PathVariable Long applicationId, @RequestBody ApplicationUpdateRequest request) {
        applicationService.update(applicationId, request);
        return ResponseEntity.ok(applicationId);
    }


}
