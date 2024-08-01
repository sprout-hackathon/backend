package com.hackathon.sprout.domain.recruitment.controller;

import com.hackathon.sprout.domain.recruitment.dto.RecruitmentCreateRequest;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentResponse;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentScrapResponse;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentSearchCondition;
import com.hackathon.sprout.domain.recruitment.service.RecruitmentService;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.service.UserService;
import com.hackathon.sprout.global.shared.AuthUtil;
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
@RequiredArgsConstructor
@RequestMapping("/api/recruitments")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> createRecruitment(@RequestBody RecruitmentCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(recruitmentService.createRecruitment(request));
    }

    @GetMapping
    public ResponseEntity<Page<RecruitmentResponse>> searchRecruitment(
        @ModelAttribute final RecruitmentSearchCondition condition,
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
    @GetMapping("/{recruitmentId}/scrap")
    public ResponseEntity<Boolean> isScrap(Authentication authentication, @PathVariable Long recruitmentId){
        String userId = AuthUtil.getUserIdFromAuth(authentication);
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(recruitmentService.checkScrap(user.getUserId(), recruitmentId) == 1);
    }

    @PostMapping("/{recruitmentId}/scrap")
    public ResponseEntity<Long> scrapRecruitment(@PathVariable Long recruitmentId){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recruitmentService.scrapRecruitment(recruitmentId));
    }

    @DeleteMapping("/{recruitmentId}/scrap")
    public ResponseEntity<Void> cancelScrap(Authentication authentication, @PathVariable Long recruitmentId){
        String userId = AuthUtil.getUserIdFromAuth(authentication);
        User user = userService.getUser(userId);
        recruitmentService.cancelScrap(user.getUserId(), recruitmentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/scrap")
    public ResponseEntity<List<RecruitmentScrapResponse>> getScrapList(){
        return ResponseEntity.ok(recruitmentService.getScrapList().stream().map(RecruitmentScrapResponse::new).toList());
    }
}
