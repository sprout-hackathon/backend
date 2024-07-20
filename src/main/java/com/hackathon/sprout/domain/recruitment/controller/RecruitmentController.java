package com.hackathon.sprout.domain.recruitment.controller;

import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import com.hackathon.sprout.domain.recruitment.domain.RecruitmentScrap;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentCreateRequest;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentResponse;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentScrapResponse;
import com.hackathon.sprout.domain.recruitment.dto.SearchCondition;
import com.hackathon.sprout.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(recruitmentService.createRecruitment(request));
    }

    @GetMapping
    public ResponseEntity<Page<RecruitmentResponse>> searchRecruitment(
        @ModelAttribute final SearchCondition condition,
        @PageableDefault(size = 10) Pageable pageable
    ){
        return ResponseEntity.status(HttpStatus.OK)
            .body(recruitmentService.getRecruitmentList(condition,pageable).map((RecruitmentResponse::new)));
    }

    @GetMapping("/{recruitmentId}")
    public ResponseEntity<RecruitmentResponse> getRecruitment(@PathVariable Long recruitmentId){
        return ResponseEntity.status(HttpStatus.OK)
            .body(new RecruitmentResponse(recruitmentService.getRecruitment(recruitmentId)));
    }
    @PostMapping("/{recruitmentId}/scrap")
    public ResponseEntity<Long> scrapRecruitment(@PathVariable Long recruitmentId){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recruitmentService.scrapRecruitment(recruitmentId));
    }

    @DeleteMapping("/scrap/{recruitmentScrapId}")
    public ResponseEntity<Void> cancelScrap(@PathVariable Long recruitmentScrapId){
        recruitmentService.cancelScrap(recruitmentScrapId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/scrap")
    public ResponseEntity<List<RecruitmentScrapResponse>> getScrapList(){
        return ResponseEntity.ok(recruitmentService.getScrapList().stream().map(RecruitmentScrapResponse::new).toList());
    }
}
