package com.hackathon.sprout.domain.country.controller;

import com.hackathon.sprout.domain.country.domain.Language;
import com.hackathon.sprout.domain.country.domain.Nation;
import com.hackathon.sprout.domain.country.service.LanguageService;
import com.hackathon.sprout.domain.country.service.NationService;
import com.hackathon.sprout.global.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nations")
@Tag(name = "국가 및 언어 정보", description = "국가 및 언어 정보를 제공하는 API")
@RequiredArgsConstructor
public class CountryController {
    private final LanguageService languageService;
    private final NationService nationService;

    @Operation(summary = "모든 국가 정보 조회", description = "모든 국가 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "국가 정보 조회 성공", content = @Content(schema = @Schema(implementation = Nation.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    @Operation(summary = "모든 언어 정보 조회", description = "모든 언어 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "언어 정보 조회 성공", content = @Content(schema = @Schema(implementation = Language.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/languages")
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }
}
