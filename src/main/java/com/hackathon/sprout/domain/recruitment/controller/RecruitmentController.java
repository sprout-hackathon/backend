package com.hackathon.sprout.domain.recruitment.controller;

import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentCreateRequest;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentResponse;
import com.hackathon.sprout.domain.recruitment.dto.SearchCondition;
import com.hackathon.sprout.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruitments")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    @PostMapping
    public ResponseEntity<Long> createRecruitment(@RequestBody RecruitmentCreateRequest request){
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(recruitmentService.createRecruitment(request));
    }

    @GetMapping
    public ResponseEntity<List<RecruitmentResponse>> searchRecruitment(
        @RequestParam String sido,
        Pageable pageable
    ){
        return ResponseEntity.status(HttpStatus.OK)
            .body(recruitmentService.getRecruitmentList(new SearchCondition(sido),pageable).map((RecruitmentResponse::new)).toList());
    }
    @GetMapping("/all")
    public ResponseEntity<List<RecruitmentResponse>> getAllRecruitments(){
        List<Recruitment> recruitmentList = recruitmentService.getRecruitmentList();
        System.out.println(recruitmentList.size());
        return ResponseEntity.status(HttpStatus.OK)
            .body(recruitmentList.stream().map((RecruitmentResponse::new)).toList());
    }

    @GetMapping("/{recruitmentId}")
    public ResponseEntity<RecruitmentResponse> getRecruitment(@PathVariable Long recruitmentId){
        return ResponseEntity.status(HttpStatus.OK)
            .body(new RecruitmentResponse(recruitmentService.getRecruitment(recruitmentId)));
    }
}
