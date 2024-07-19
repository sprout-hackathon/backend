package com.hackathon.sprout.domain.hospital.controller;

import com.hackathon.sprout.domain.hospital.dto.HospitalResponse;
import com.hackathon.sprout.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping()
    public ResponseEntity<List<HospitalResponse>> searchHospital(@RequestParam String keyword){
        log.debug(keyword);
        return ResponseEntity.status(HttpStatus.OK)
                .body(hospitalService.getHospitalList(keyword).stream().map((HospitalResponse::new)).toList());
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<HospitalResponse> searchHospital(@PathVariable Long hospitalId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new HospitalResponse(hospitalService.getHospital(hospitalId)));
    }
}
