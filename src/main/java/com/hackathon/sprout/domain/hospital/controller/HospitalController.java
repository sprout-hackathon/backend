package com.hackathon.sprout.domain.hospital.controller;

import com.hackathon.sprout.domain.hospital.dto.HospitalResponse;
import com.hackathon.sprout.domain.hospital.service.HospitalService;
import com.hackathon.sprout.global.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@Tag(name = "병원", description = "병원 정보 관련 API")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {
    private final HospitalService hospitalService;

    @Operation(summary = "병원 검색", description = "키워드를 사용하여 병원을 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "병원 검색 성공", content = @Content(schema = @Schema(implementation = HospitalResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping()
    public ResponseEntity<List<HospitalResponse>> searchHospital(@RequestParam String keyword) {
        log.debug(keyword);
        return ResponseEntity.status(HttpStatus.OK)
                .body(hospitalService.getHospitalList(keyword).stream().map(HospitalResponse::new).toList());
    }

    @Operation(summary = "병원 상세 정보 조회", description = "병원 ID를 사용하여 병원 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "병원 상세 정보 조회 성공", content = @Content(schema = @Schema(implementation = HospitalResponse.class))),
            @ApiResponse(responseCode = "404", description = "병원을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{hospitalId}")
    public ResponseEntity<HospitalResponse> searchHospital(@PathVariable Long hospitalId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new HospitalResponse(hospitalService.getHospital(hospitalId)));
    }
}
