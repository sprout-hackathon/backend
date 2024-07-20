package com.hackathon.sprout.domain.workhistory.controller;


import com.hackathon.sprout.domain.user.service.UserService;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryRequest;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryResponse;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryUpdateRequest;
import com.hackathon.sprout.domain.workhistory.service.WorkHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/work-histories")
public class WorkHistoryController {
    private final WorkHistoryService workHistoryService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> createWorkHistory(@RequestBody WorkHistoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(workHistoryService.createWorkHistory(userService.getUserFromAuth(),request));
    }

    @GetMapping
    public ResponseEntity<List<WorkHistoryResponse>> getWorkHistoryList() {
        return ResponseEntity.ok(workHistoryService.getWorkHistoryList(userService.getUserFromAuth()).stream().map(WorkHistoryResponse::new).toList());
    }

    @GetMapping("/{workHistoryId}")
    public ResponseEntity<WorkHistoryResponse> getWorkHistory(@PathVariable Long workHistoryId){
        return ResponseEntity.ok(new WorkHistoryResponse(workHistoryService.getWorkHistory(workHistoryId)));
    }

    @PatchMapping("/{workHistoryId}")
    public ResponseEntity<Long> updateWorkHistory(@PathVariable Long workHistoryId, @RequestBody WorkHistoryUpdateRequest request){
        workHistoryService.update(workHistoryId, request);
        return ResponseEntity.ok(workHistoryId);
    }

    @DeleteMapping("/{workHistoryId}")
    public ResponseEntity<Void> deleteWorkHistory(@PathVariable Long workHistoryId){
        workHistoryService.deleteWorkHistory(workHistoryId);
        return ResponseEntity.noContent().build();
    }




}
