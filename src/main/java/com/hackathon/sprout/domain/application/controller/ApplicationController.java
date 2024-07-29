package com.hackathon.sprout.domain.application.controller;

import com.hackathon.sprout.domain.application.dto.ApplicationCreateRequest;
import com.hackathon.sprout.domain.application.dto.ApplicationResponse;
import com.hackathon.sprout.domain.application.dto.ApplicationStateResponse;
import com.hackathon.sprout.domain.application.dto.ApplicationUpdateRequest;
import com.hackathon.sprout.domain.application.enums.ApplicationState;
import com.hackathon.sprout.domain.application.service.ApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    @Tag(name = "지원 내역 등록", description = "지원 내역을 등록합니다.")
    public ResponseEntity<Long> registerApplication(@RequestBody ApplicationCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.registerApplication(request));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getApplicationList(){
        return ResponseEntity.ok(applicationService.getApplicationList().stream().map(ApplicationResponse::new).toList());
    }

    @GetMapping("/states")
    public ResponseEntity<List<ApplicationStateResponse>> getApplicationStateList(){
        return ResponseEntity.ok(Arrays.stream(ApplicationState.values()).map(ApplicationStateResponse::new).toList());
    }

    @PatchMapping("/{applicationId}")
    public ResponseEntity<Long> updateApplication(@PathVariable Long applicationId, @RequestBody ApplicationUpdateRequest request){
        applicationService.update(applicationId, request);
        return ResponseEntity.ok(applicationId);
    }


}
