package com.hackathon.sprout.domain.workhistory.service;

import com.hackathon.sprout.domain.hospital.service.HospitalService;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.service.UserService;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryRequest;
import com.hackathon.sprout.domain.workhistory.repository.WorkHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkHistoryService {
    private final HospitalService hospitalService;
    private final UserService userService;
    private final WorkHistoryRepository workHistoryRepository;

    public Long createWorkHistory(WorkHistoryRequest workHistoryRequest){
        return workHistoryRepository.save(workHistoryRequest.toEntity(
                hospitalService.getHospital(workHistoryRequest.getHospitalId()),
                userService.getUserFromAuth())).getWorkHistoryId();
    }

    public Long createWorkHistory(User user, WorkHistoryRequest workHistoryRequest){
        return workHistoryRepository.save(workHistoryRequest.toEntity(
                hospitalService.getHospital(workHistoryRequest.getHospitalId()),
                user)).getWorkHistoryId();
    }
}
